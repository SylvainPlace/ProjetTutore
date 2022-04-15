<script setup>
import { onMounted, reactive, ref } from "vue";
import traitement from "@/Traitement.js";
let medicChoisi = ref(0);
const listeunitFreq = reactive([]);
const listeunitDuree = reactive([]);
const listTraitement = reactive([]);
const listeSearch = reactive([]);

onMounted(() => {
  console.log("oui");
  getFrequence();
  getDuree();
  lesMedicaments("");
});

function lesMedicaments(medic) {
  let fetchOptions = { method: "GET" };
  console.log("ici " + medic);
  fetch("/api/medicamentsByName?mot=" + medic)
    .then((response) => {
      return response.json();
    })

    .then((dataJSON) => {
      console.log(dataJSON);
      listeSearch.splice(0, listeSearch.length);
      dataJSON.forEach((v) => listeSearch.push(v));
    })

    .catch((error) => {
      console.log(error);
    });
}
function valeurMedicChoisi() {
  medicChoisi = document.getElementById("selectmedic").value;
  console.log(medicChoisi);
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
      console.log(dataJSON);
      dataJSON.forEach((v) => listeunitFreq.push(v));
      console.log(listeunitFreq);
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
      console.log(dataJSON);
      dataJSON.forEach((v) => listeunitDuree.push(v));
      console.log(listeunitDuree);
    })
    .catch((error) => {
      //console.log(error);
    });
}
function listTraitementEvent() {
  let dureeUnite = document.getElementById("dureeUnite").value;
  console.log("ici " + dureeUnite);
  let frequenceUnite = document.getElementById("freqUnite").value;
  let duree = document.getElementById("duree").value;
  let frequence = document.getElementById("frequence").value;
  let quantite = document.getElementById("quantite").value;
  listTraitement.push(
    new traitement(
      medicChoisi,
      duree,
      dureeUnite,
      frequence,
      frequenceUnite,
      quantite
    )
  );
  
  console.log(listTraitement);
  console.log(listTraitement[2]._duree);
  console.log(listTraitement[0]._unitduree);
}
</script>
<template>
  <div>
    <h4>Rechercher votre médicament :</h4>
    <form @submit.prevent="listTraitementEvent(dureeUnite)">
      <input id="listeMedic" v-model="medic" @keyup="lesMedicaments(medic)" />
      <select id="selectmedic" @change="valeurMedicChoisi()">
        <option disabled selected>
          Choissisez votre Medicament dans la liste
        </option>
        <option v-for="search of listeSearch" :value="search.id">
          {{ search.nom_medic }}
        </option>
      </select>

      <h4>Posologie ?</h4>
      <div>
        <h5>Durée de traitement</h5>
        <input id="duree" type="number" min="0" max="100" />
        <select id="dureeUnite" class="unite">
          <option disabled selected>
            Choissisez votre Unite de Duree dans la liste
          </option>
          <option v-for="(duree, index) of listeunitDuree" :value="index">
            {{ duree }}
          </option>
        </select>
      </div>
      <div>
        <h5>Fréquence</h5>
        <input id="frequence" type="number" min="0" max="10" />
        <h5>fois par</h5>
        <select id="freqUnite">
          <option disabled selected>
            Choissisez votre Unite de Frequence dans la liste
          </option>
          <option v-for="(freq, index) of listeunitFreq" :value="index">
            {{ freq }}
          </option>
        </select>
      </div>
      <div>
        <h5>Quantité</h5>
        <input id="quantite" type="number" step=".5" min="0" max="15" />
        <h5>dose(s) par prise</h5>
      </div>

      <input id="valider" type="submit" value="ajouter" />
    </form>
  </div>
</template>
<style scopped>
</style>