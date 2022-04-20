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
      //console.log(error);
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

function listTraitementEvent() {
  let dureeUnite = document.getElementById("dureeUnite").value;
  //console.log("ici " + dureeUnite);
  let frequenceUnite = document.getElementById("freqUnite").value;
  let duree = document.getElementById("duree").value;
  let frequence = document.getElementById("frequence").value;
  let quantite = document.getElementById("quantite").value;
  let patient = document.getElementById("selectPatient").value;
  console.log(medicChoisi)
  let url = "/api/medicaments/" + medicChoisi;
  let fetchOptions = { method: "Get" };
  let medicNom;
  fetch(url, fetchOptions)
    .then((response) => response.json())
    .then((json) => {
      medicNom = json.nom_medic;
      console.log(maladieChoisi)
      let url2 = "/api/maladies/" + maladieChoisi;
      let fetchOptions = { method: "Get" };
      let maladieNom;
      fetch(url2, fetchOptions)
        .then((response) => response.json())
        .then((json) => {
          maladieNom = json.nom_maladie;
          console.log(maladieNom);
          listTraitement.push(
            new traitement(
              medicChoisi,
              medicNom,
              maladieChoisi,
              maladieNom,
              patienchoisi,
              duree,
              dureeUnite,
              frequence,
              frequenceUnite,
              quantite
            )
          );
          console.log(listTraitement);
        })
        .catch((error) => alert(error));
    })
    .catch((error) => alert(error));
}

</script>
<template>
    <select
      id="selectPatient"
      v-model="patienchoisi"
      @change="valeurPatientChoisi()"
    >
      <option disabled selected>
        Choissisez votre utilisateur dans la liste
      </option>
      <option v-for="patient of patients" :value="patient.id">
        {{ patient.nom }} {{ patient.prenom }}
      </option>
    </select>
    <select id="selectMaladie" @change="valeurMaladieChoisi($event.target.value)">
      <option disabled selected>Choissisez votre maladie dans la liste</option>
      <option v-for="maladie of maladies" :value="maladie.id">
        {{ maladie.nom_maladie }}
      </option>
    </select>
  <div class="formulaireTraitement">
    <h4 id="recherche">Recherchez votre médicament :</h4>
    <form @submit.prevent="listTraitementEvent(dureeUnite)">
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
        <select class="select" id="selectduree" >
          <option disabled selected>
           Jour
          </option>
          <option v-for="(duree, index) of listeunitDuree" :value="index">
            {{ duree }}
          </option>
        </select>
      </div>
      <div >
        <h5 id="frequenceT">Fréquence</h5>
        <input id="frequence" type="number" min="0" max="10" />
        <h5 id="fois">fois par</h5>
        <select class="select" id="selectF">
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
      <div >
      <input id="valider" type="submit" value="Ajouter" />
      </div>
    </form>
  </div>
  <div class="container mt-3">
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Liste des médicaments ajoutés</th>
          <th>Actions possibles</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(traitement, index) of listTraitement" :key="index">
          <td>
            Nom du médicament : {{ traitement._medicNom }} <br />
            Nom de la maladie : {{ traitement._maladieNom }} <br />
            Durée : {{ traitement._duree }} {{ traitement._unitduree }} <br />
            Fréquence : {{ traitement._freq }} {{ traitement._unitfreq }} <br />
            Quantité : {{ traitement._qte }} dose(s) par prises<br />
          </td>
          <td>
            <button @click="deleteMedicament(medicament)">Supprimer</button>
            <button @click="modifMedicament(medicament)">Modifier</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<style scopped>
.formulaireTraitement{
  position : relative ; 
  left : 13px;
  top : 82px;
  height : 430px;
  width : 447px;
  border: 3px solid #B48B75;
   background: #D09478;
   color : white;
   text-align : center;
}
.select {
	position: relative; 
	background-color: white;
	border: #B48B75  1px solid;
	margin: 0 0 1.5em 0;
	overflow: hidden; 
  
}

#dureeTraitement{
  position : relative;
  width : 189px;
  left : 9px;
}
#selectduree{
  position : relative;
  left : -50px;}
#choix{
   position : relative;
  left : -62px;
  top :0px;
}

#poso{
  position : relative;
  left : -104px;
}

#recherche{
  position : relative;
  left : -36px;
  top : 5px;
}

#listeMedic{
  position : relative;
  left : -40px;
}

#selectrech{
   position : relative;
  left : -27px;
}

#frequenceT{
  position : relative;
  left : -158px;
}

#frequence{
  position : relative;
  left : -116px;
}

#fois{
  position : relative;
  left : 18px;
  top : -27px; 
}

#selectF{
    position : relative;
  left : 114px;
  top : -57px; 
}

#quantitetxt{
  position : relative;
  left : -167px;
  top : -61px; 
}

#quantite{
  position : relative;
  left : -115px;
  top : -61px; 
}

#dosetxt{
   position : relative;
  left : 61px;
  top : -89px; 
}

#valider{
  color : black ;
    border: 3px solid #B48B75;
   background: white;
    position : relative;
  left : 6px;
  top : -80px; 
}
</style>