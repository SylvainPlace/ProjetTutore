<template>
  <h3>Patient à ajouter</h3>

  <form
    @submit.prevent="
      $emit('addPatient', nom, prenom, adresse_mail, mdp, date_de_naiss)
    "
  >
    <input type="text" v-model="nom" placeholder="Nom ?" />
    <input type="text" v-model="prenom" placeholder="Prenom ?" />
    <input type="text" v-model="adresse_mail" placeholder="Adresse email ?" />
    <input type="text" v-model="mdp" placeholder="Mots de passe ?" />
    <input
      type="text"
      v-model="date_de_naiss"
      placeholder="Date de naissance ?"
    />
    <select>
      <option v-for="category of listeC">{{ category }}</option>
    </select>
    <input type="submit" value="Ajouter un patient" />
  </form>
</template>

<script setup>
import { onMounted, reactive } from "vue";
const listeC = reactive([]);

onMounted(() => {
  console.log("oui");
  getCategorie();
});

function getCategorie(event) {
  let url = "/api/allCategorie";
  let fetchOptions = { method: "Get" };
  fetch(url, fetchOptions)
    .then((response) => {
      return response.json();
    })
    .then((dataJSON) => {
      console.log(dataJSON);
      dataJSON.forEach((v) => listeC.push(v));
      console.log(listeC);
    })
    .catch((error) => {
      //console.log(error);
    });
}
</script>