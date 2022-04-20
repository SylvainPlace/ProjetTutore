<script setup>
import { onMounted, reactive, ref } from "vue";
import traitement from "@/Traitement.js";
import TabRecapMedic11 from "@/components/TabRecapMedic11.vue";
let medicChoisi = ref(0);
let patienchoisi = ref(0);
let maladieChoisi = ref(0);
const listeunitFreq = reactive([]);
const listeunitDuree = reactive([]);
const listTraitement = reactive([]);
const listeSearch = reactive([]);
const patients = reactive([]);
const maladies = reactive([]);

onMounted(() => {
  getFrequence();
  getDuree();
  lesMedicaments("");
  getPatients();
  getMaladies();
});

function getPatients(event) {
  let url = "/api/ListePatient/";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => response.json())
    .then((json) => {
      json.forEach((v) => patients.push(v));
    })
    .catch((error) => alert(error));
}

function getMaladies(event) {
  let url = "/api/maladies";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => response.json())
    .then((json) => {
      let results = json._embedded.maladies;
      results.forEach((v) => maladies.push(v));
    })
    .catch((error) => alert(error));
}

function valeurPatientChoisi() {
  patienchoisi = document.getElementById("selectPatient").value;
  return patienchoisi;
}

function valeurMaladieChoisi(id) {
  maladieChoisi = id;
  return maladieChoisi;
}

function lesMedicaments(medic) {
  let fetchOptions = { method: "GET" };
  fetch("/api/medicamentsByName?mot=" + medic)
    .then((response) => {
      return response.json();
    })

    .then((dataJSON) => {
      listeSearch.splice(0, listeSearch.length);
      dataJSON.forEach((v) => listeSearch.push(v));
    })

    .catch((error) => {
    });
}

function valeurMedicChoisi() {
  medicChoisi = document.getElementById("selectmedic").value;
  return medicChoisi;
}

function getFrequence(event) {
  let url = "/api/allUniteFreq";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
      dataJSON.forEach((v) => listeunitFreq.push(v));
    })
    .catch((error) => {
    });
}

function getDuree(event) {
  let url = "/api/allUniteDuree";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
      dataJSON.forEach((v) => listeunitDuree.push(v));
    })
    .catch((error) => {
    });
}

function listTraitementEvent(
  medicChoisi,
  patienchoisi,
  maladieChoisi,
  duree,
  dureeUnite,
  frequence,
  unitfreq,
  quantite
) {
  listTraitement.push(
    new traitement(
      medicChoisi,
      maladieChoisi,
      patienchoisi,
      duree,
      dureeUnite,
      frequence,
      unitfreq,
      quantite
    )
  );
}

function supprimer(index) {
  listTraitement.splice(index, 1);
  return listTraitement;
}

function putMedicament() {
  listTraitement.forEach((item, index) => {
    postUnSoigner(
      item._medic,
      item._maladie,
      item._utilisateurs,
      item._unitduree,
      item._duree,
      item._unitfreq,
      item._freq,
      item._qte,
      item._date
    );
  });
}

function postUnSoigner(
  medic,
  maladie,
  utilisateurs,
  unitduree,
  duree,
  unitfreq,
  freq,
  qte,
  date
) {
  let url = "/api/saveSoigner";
  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify({
      datecreation: date,
      doseparprise: qte,
      maladie: maladie,
      medicament: medic,
      unitduree: unitduree,
      unitfreq: unitfreq,
      utilisateur: utilisateurs,
      valduree: duree,
      valfreq: freq,
    }),
  };
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
    });
}
</script>


<template>
  
  <div id="formulaireETtableau">

    <!--Case : Choisissez votre patient -->
  <div class="formulaireTraitement">
    <h4 id="patient">Choisissez votre patient : </h4>
    <select id="selectPatient" v-model="patienchoisi" @change="valeurPatientChoisi()">
      <option disabled selected>

        Choissisez votre utilisateur dans la liste
      </option>
      <option v-for="patient of patients" :value="patient.id">
        {{ patient.nom }} {{ patient.prenom }}
      </option>
    </select>
    <h4 id="indic">Indiquez la maladie : </h4>
    <select id="selectMaladie" @change="valeurMaladieChoisi($event.target.value)" v-model="maladieChoisi">
      <option disabled selected>Choissisez votre maladie dans la liste</option>
      <option v-for="maladie of maladies" :value="maladie.id">
        {{ maladie.nom_maladie }}
      </option>
    </select>
    <h4>Rechercher votre médicament :</h4>
    <form
      @submit.prevent="
        listTraitementEvent(
          medicChoisi,
          patienchoisi,
          maladieChoisi,
          duree,
          dureeUnite,
          frequence,
          unitfreq,
          quantite
        )
      "
    >
      <input id="listeMedic" v-model="medic" @keyup="lesMedicaments(medic)" />
      <select class="select" id="selectrech" @change="valeurMedicChoisi()">
        <option disabled selected>
          Selectionnez
        </option>
        <option v-for="search of listeSearch" :value="search.id">
          {{ search.nom_medic }}
        </option>
      </select>

      <h4 id="poso">Entrez la posologie</h4>
      <div>
        <h5 id="dureeTraitement">Durée de traitement</h5>
        <input id="choix" type="number" min="0" max="100" />
        <select class="select" id="selectduree">
          <option disabled selected>
            Jour
          </option>
          <option v-for="(duree, index) of listeunitDuree" :value="index">
            {{ duree }}
          </option>
        </select>
      </div>
      <div>
        <h5 id="frequenceT">Fréquence</h5>
        <input
          id="frequence"
          type="number"
          min="0"
          max="10"
          v-model="frequence"
        />
        <h5 id="fois">fois par</h5>
        <select class="select" id="selectF" v-model="unitfreq">
          <option disabled selected>
            Jour
          </option>
          <option v-for="(freq, index) of listeunitFreq" :value="index">
            {{ freq }}
          </option>
        </select>
      </div>
      <div>
        <h5 id="quantitetxt">Quantité</h5>
        <input id="quantite" type="number" step=".5" min="0" max="15" />
        <h5 id="dosetxt">dose(s) par prise</h5>
      </div>
      <div>
        <input id="valider" type="submit" value="Ajouter" />
      </div>
    </form>
  </div>

  <!--Case : Tableau -->
  <div class="tableau"> 

    <h2> Liste des médicaments en cours d'ajout </h2>
	
	<table border="1" id="leTableau" class="table table-bordered table-sm table-hover">
		<thead>
			<tr>
				<th colspan="4" id="liste">Liste de vos médicaments en cours de saisie</th>
				<th id="action">Réécrire une saisie</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="(ceTraitement, index) in listTraitement" 
      :key="index"
        >
				<td id="1">
          Nom du médicament : <br/>
          Maladie traité :
        </td>
        <td id="2">
          {{ceTraitement._medic}} <br/>
          {{ceTraitement._maladie}}
        </td>
        <td id="3">
          Durée de prise : <br/>
          
          Fréquence de prise : <br/>
          
          Quantité à prendre :
        </td>
        <td id="4">
          {{ceTraitement._duree}} {{ceTraitement._unitduree}} <br/>
          
          {{ceTraitement._freq}} / {{ceTraitement._unitfreq}} <br/>
          
          {{ceTraitement._qte}} / Prise<br/>
        </td>
				<td id="5">
          <!-- Bouton -->
			    <input type="button" value="Supprimer" id="enregistrer" @click="supprimer(index)" />
        </td>
			</tr>
		</tbody>
	</table>

  <!-- Bouton -->
	<input type="button" id="enregistrer" value="Valider la liste et l'enregistrer" @click="putMedicament()" />

  </div>
  </div>

</template>
<style scopped>

/* CSS formulaire + tableau */ 

#formulaireETtableau {
  display: flex;
  justify-content: space-around;
}

/* CSS Formulaire */

.formulaireTraitement{
  position : relative ; 
  /*left : 13px;*/
  margin-left: 5%;
  top : 8px;
  height : 612px;
  width : 483px;
  border: 3px solid #B48B75;
  background: -webkit-linear-gradient(to left, #D09478, #f5bba0);
  background: linear-gradient(to left, #f5bba0, #D09478);

  color: white;
  text-align: center;
  border-radius: 10px 100px / 120px;
}

.select {
  position: relative;
  background-color: white;
  border: #B48B75 1px solid;
  border-radius: 30px;
  margin: 0 0 1.5em 0;
  overflow: hidden;

}

#dureeTraitement {
  position: relative;
  width: 189px;
  left: 16px;
  top: 48px;
}

#selectduree {
  position: relative;
  left: -50px;
  top: 48px;
  border-radius: 30px;
}

#choix {
  position: relative;
  left: -62px;
  top: 48px;
  border-radius: 10px;
}

#poso {
  position: relative;
  left: -116px;
  top: 43px;
}

#recherche {
  position: relative;
  left: -48px;
  top: 57px;
}

#listeMedic {
  position: relative;
  left: -40px;
  top: 48px;
  border-radius: 10px;
}

#selectrech {
  position: relative;
  left: -27px;
  top: 48px;
  border-radius: 10px;
}

#frequenceT {
  position: relative;
  left: -164px;
  top: 48px;
}

#frequence {
  position: relative;
  left: -116px;
  top: 48px;
  border-radius: 10px;
}

#fois {
  position: relative;
  left: 215px;
  top: 21px;
  width: 86px;
}

#selectF {
  position: relative;
  left: 114px;
  top: -9px;
}

#quantitetxt {
  position: relative;
  left: -174px;
  top: -13px;
}

#quantite {
  position: relative;
  left: -115px;
  top: -13px;
  border-radius: 10px;
}

#dosetxt {
  position: relative;
  left: 221px;
  top: -41px;
  width: 162px;
}

#valider {
  color: black;
  border: 3px solid #B48B75;
  border-radius: 10px 100px / 120px;
  background: white;
  position: relative;
  left: 6px;
  top: -32px;
}


#selectPatient {
  position: relative;
  background-color: white;
  border: black 1px solid;
  border-radius: 10px;
  top: 7px;
  left: -43px;

}

#selectMaladie {
  position: relative;
  border-radius: 10px;
  background-color: white;
  border: black 1px solid;
  top: 31px;
  left: -50px;
}

#patient {
  position: relative;
  left: -85px;
}

#indic {
  position: relative;
  left: -105px;
  top: 25px;
}


/* CSS Tableau */

.tableau{
  position : relative ; 
  top : 8px;
  min-height : 612px;
  border: 5px solid #B48B75;
  /*background: -webkit-linear-gradient(to left, #D09478, #f5bba0);
    background: linear-gradient(to left, #f5bba0, #D09478);*/
  background-color: white;

   color : #B48B75 ;
   text-align : center;
   border-radius: 100px 10px / 120px;

   padding: 5%;
  margin-right: 5%; 
  margin-left: 5%;  
}

#leTableau {
  width : 800px;

  table-layout: auto;
width: 100%;
}

#liste {
  width: 700px;
}

tbody {
  border: 1px solid #B48B75 ;
}

tr {
  border: 1px solid #B48B75 ;
}

#enregistrer{
  color : black ;
    border: 3px solid #B48B75;
    border-radius: 10px 100px / 120px;
   background: white;
}

</style>