<template>
  <h3>Patient à ajouter</h3>

  <form
    @submit.prevent="
      handlerAddPatient(
        nom,
        prenom,
        adresse_mail,
        mdp,
        date_de_naiss,
        categorie
      )
    "
    class="row g-3"
  >
    <div class="col-md-5">
      <label for="Nom" class="form-label">Nom : </label>
      <input
        type="text"
        v-model="nom"
        placeholder="Dupont"
        class="form-control"
        id="Nom"
      />
    </div>
    <div class="col-md-5">
      <label for="Prenom" class="form-label" form-floating>Prenom :</label>
      <input
        type="text"
        v-model="prenom"
        placeholder="Jean"
        class="form-control"
        id="Prenom"
      />
    </div>
    <div class="col-md-4">
      <label for="mail" class="form-label">Adresse e-mail :</label>
      <input
        type="email"
        v-model="adresse_mail"
        placeholder="email@example.com"
        class="form-control"
        id="mail"
      />
    </div>
    <div class="col-md-5">
      <label for="mdp" class="form-label">Mot de passe :</label>
      <input
        type="password"
        v-model="mdp"
        placeholder="********"
        class="form-control"
        id="mdp"
      />
    </div>
    <div class="col-md-2">
      <label for="ddn" class="form-label">Date de naissance :</label>
      <input
        type="date"
        v-model="date_de_naiss"
        placeholder="01/01/2000"
        class="form-control"
        id="ddn"
      />
    </div>
    <div class="col-md-4">
      <label for="type" class="form-label">Categorie d'utilisateur :</label>
      <select class="form-select" id="type" v-model="categorie">
        <option selected disabled>Type</option>
        <option v-for="category of listeC">{{ category }}</option>
      </select>
    </div>
    <div class="col-10">
      <input type="submit" value="Ajouter un patient" class="btn btn-primary" />
    </div>
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

function handlerAddPatient(
  nom,
  prenom,
  adresse_mail,
  mdp,
  date_de_naiss,
  categorie
) {
  let url = "/api/utilisateurs";
  let myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");
  const fetchOptions = {
    method: "POST",
    headers: myHeaders,
    body: JSON.stringify({
      adresse_mail: adresse_mail,
      categorie: categorie,
      date_de_naiss: date_de_naiss,
      mdp: mdp,
      nom: nom,
      prenom: prenom,
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