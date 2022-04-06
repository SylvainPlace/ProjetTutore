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
    id="form"
  >
    <div class="col-md-6">
      <label for="Nom" class="form-label">Nom : </label>
      <input
        type="text"
        v-model="nom"
        placeholder="Dupont"
        class="form-control"
        id="Nom"
        required
      />
    </div>
    <div class="col-md-6">
      <label for="Prenom" class="form-label" form-floating>Prenom :</label>
      <input
        type="text"
        v-model="prenom"
        placeholder="Jean"
        class="form-control"
        id="Prenom"
        required
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
        required
      />
    </div>
    <div class="col-md-6">
      <label for="mdp" class="form-label">Mot de passe :</label>
      <input
        type="password"
        v-model="mdp"
        placeholder="********"
        class="form-control"
        id="mdp"
        @input="passwordStrengh()"
        required
      />
      <span id="msgPasswd"></span>
    </div>
    <div class="col-md-2">
      <label for="ddn" class="form-label">Date de naissance :</label>
      <input
        type="date"
        v-model="date_de_naiss"
        placeholder="01/01/2000"
        class="form-control"
        id="ddn"
        required
      />
    </div>
    <div class="col-md-4">
      <label for="type" class="form-label">Categorie d'utilisateur :</label>
      <select class="form-select" id="type" v-model="categorie" required>
        <option selected disabled>Type</option>
        <option v-for="category of listeC">{{ category }}</option>
      </select>
    </div>
    <div class="col-12">
      <input type="submit" value="Ajouter un patient" class="btn btn-primary" />
    </div>
  </form>
  <span id="texte"></span>
</template>

<script setup>
import { onMounted, reactive, ref } from "vue";
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
      document.getElementById("texte").innerHTML =
        "Le patient " +
        nom +
        " " +
        prenom +
        " a bien été ajouté à la base de données";
      document.getElementById("form").reset();
    });
}

function passwordStrengh(event) {
  let entree = document.getElementById("mdp").value;
  let msg = document.getElementById("msgPasswd");
  if (entree.length < 3) {
    msg.textContent = "Mot de passe faible";
    msg.style.color = "red";
  } else {
    if (entree.length < 6) {
      msg.textContent = "Mot de passe moyen";
      msg.style.color = "orange";
    } else {
      msg.textContent = "Mot de passe fort";
      msg.style.color = "green";
    }
  }
}
</script>