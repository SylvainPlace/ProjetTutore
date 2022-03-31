<template>
  <AddPatient @addPatient="handlerAddPatient" />
</template>

<script setup>
import AddPatient from "@/components/AddPatient.vue";

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
