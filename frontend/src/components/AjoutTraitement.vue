<script setup>
import { onMounted, reactive, ref } from "vue";
import traitement from "@/Traitement.js";
import TabRecapMedic11 from "@/components/TabRecapMedic11.vue";
const listeunitFreq = reactive([]);
const listeunitDuree = reactive([]);
const listTraitement = reactive([]);
const listeSearch = reactive([]);
const patients = reactive([]);
const maladies = reactive([]);

onMounted(() => {
  getFrequence();
  getDuree();
  getPatients();
  getMaladies();
  lesMedicaments("");
});

function getPatients(event) {
  let url = "/api/utilisateurs";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => response.json())
    .then((json) => {
      let results = json._embedded.utilisateurs;
      results.forEach((v) => patients.push(v));
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
      //console.log(error);
    });
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
      //console.log(error);
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
      //console.log(error);
    });
}

function listTraitementEvent(
  medicChoisi,
  patienchoisi,
  maladieChoisi,
  duree,
  dureeUnite,
  frequence,
  frequenceUnite,
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
      frequenceUnite,
      quantite
    )
  );
  console.log(listTraitement);
}

function deleteMedicament(index) {
  listTraitement.splice(index, 1);
  return listTraitement;
}

function putMedicament() {
  listTraitement.forEach((item, index) => {
    console.log(item._medic);
    postUnSoigner(
      item._medic,
      item._maladie,
      item._utilisateurs,
      item._unitduree,
      item._duree,
      item._unitefreq,
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
  let url = "/api/soigners";
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
      uniteduree: unitduree,
      unitefreq: unitfreq,
      utilisateur: utilisateurs,
      valduree: duree,
      valfreq: freq,
    }),
  };
  console.log(fetchOptions);
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
      console.log(dataJSON);
    });
}

function supprimer(index) {
  listTraitement.splice(index, 1);
  return listTraitement;
}
</script>


<template>
  <div>
    <select id="selectPatient" v-model="patienchoisi">
      <option disabled selected>
        Choissisez votre utilisateur dans la liste
      </option>
      <option v-for="patient of patients">
        {{ patient.nom }} {{ patient.prenom }}
      </option>
    </select>
    <select id="selectMaladie" v-model="maladieChoisi">
      <option disabled selected>Choissisez votre maladie dans la liste</option>
      <option v-for="maladie of maladies">
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
          frequenceUnite,
          quantite
        )
      "
    >
      <input id="listeMedic" v-model="medic" @keyup="lesMedicaments(medic)" />
      <select id="selectmedic" v-model="medicChoisi">
        <option disabled selected>
          Choissisez votre Medicament dans la liste
        </option>
        <option v-for="search of listeSearch">
          {{ search.nom_medic }}
        </option>
      </select>
      <h4>Posologie ?</h4>
      <div>
        <h5>Durée de traitement</h5>
        <input id="duree" type="number" min="0" max="100" v-model="duree" />
        <select id="dureeUnite" class="unite" v-model="dureeUnite">
          <option disabled selected>
            Choissisez votre Unite de Duree dans la liste
          </option>
          <option v-for="(duree, index) of listeunitDuree">
            {{ duree }}
          </option>
        </select>
      </div>
      <div>
        <h5>Fréquence</h5>
        <input
          id="frequence"
          type="number"
          min="0"
          max="10"
          v-model="frequence"
        />
        <h5>fois par</h5>
        <select id="freqUnite" v-model="frequenceUnite">
          <option disabled selected>
            Choissisez votre Unite de Frequence dans la liste
          </option>
          <option v-for="(freq, index) of listeunitFreq">
            {{ freq }}
          </option>
        </select>
      </div>
      <div>
        <h5>Quantité</h5>
        <input
          id="quantite"
          type="number"
          step=".5"
          min="0"
          max="15"
          v-model="quantite"
        />
        <h5>dose(s) par prise</h5>
      </div>
      <input id="valider" type="submit" value="ajouter" />
    </form>
  </div>
  <div class="container mt-3">
  </div>

  <div> <!-- tableau -->

    <h2> Liste des médicaments en cours d'ajout </h2>
	
	<table border="1">
		<thead>
			<tr>
				<th colspan="4">Liste de vos médicaments en cours de saisie</th>
				<th>Réécrire une saisie</th>
			</tr>
		</thead>
		<tbody>
			<tr v-for="(ceTraitement, index) in listTraitement" 
      :key="index"
        >
				<td>
          Nom du médicament : <br/>
          Maladie traité :
        </td>
        <td>
          {{ceTraitement._medic}} <br/>
          {{ceTraitement._maladie}}
        </td>
        <td>
          Durée de prise : <br/>
          
          Fréquence de prise : <br/>
          
          Quantité à prendre :
        </td>
        <td>
          {{ceTraitement._duree}} {{ceTraitement._unitduree}} <br/>
          
          {{ceTraitement._freq}} / {{ceTraitement._unitfreq}} <br/>
          
          {{ceTraitement._qte}} / Prise<br/>
        </td>
				<td>
          <!-- Bouton -->
			    <input type="button" value="Supprimer" @click="supprimer(index)" />
        </td>
			</tr>
		</tbody>
	</table>

  <!-- Bouton -->
	<input type="button" value="Valider" @click="putMedicament()" />

  </div>

</template>
<style scopped>
</style>