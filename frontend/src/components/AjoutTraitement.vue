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
  console.log("wesg"+unitfreq);
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
  console.log(fetchOptions);
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
      console.log(dataJSON);
    });
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
          unitfreq,
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
        <select id="freqUnite" v-model="unitfreq">
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
            Nom du médicament : {{ traitement._medic }} <br />
            Nom de la maladie : {{ traitement._maladie }} <br />
            Durée : {{ traitement._duree }} {{ traitement._unitduree }} <br />
            Fréquence : {{ traitement._freq }} fois par
            {{ traitement._unitfreq }} <br />
            Quantité : {{ traitement._qte }} dose(s) par prises<br />
          </td>
          <td>
            <button @click="deleteMedicament(index)">Supprimer</button>
          </td>
        </tr>
        <button @click="putMedicament()">Valider la saisie</button>
      </tbody>
    </table>
  </div>
</template>
<style scopped>
</style>