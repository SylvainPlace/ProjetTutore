<script setup>
import { onMounted, reactive } from "vue";
function lesMedicaments(event) {
  let fetchOptions = { method: "GET" };

  fetch("http://localhost:8989/api/medicamentsByName")
    .then((response) => {
      return response.json();
    })

    .then((dataJSON) => {
      let results = dataJSON._embedded.medicaments;
      console.log(results);
      let textHtml;
      for (medic of results) {
        textHtml +=
          "<option value=" +
          medic.url +
          ">" +
          medic.nom_medic +
          " " +
          medic.info_prise +
          "</option>";
      }

      document.getElementById("listeMedic").innerHTML = textHtml;
    })

    .catch((error) => {
      console.log(error);
    });
}
onMounted(() => {
  lesMedicaments();
});
</script>
<template>
  <div>
    <h4>Rechercher votre médicament :</h4>
    <form
      class="leformulaire"
      @submit.prevent="$emit('eventAjouterTraitement', medic, dureeUnite, duree, quantite,frequence,frequenceUnite)"
    >
      <input id="listeMedic" v-model="medic" @keyup="$emit('lesMedicaments', medic)" />

      
      <h4>Posologie ?</h4>
      <div>
        <h5>Durée de traitement</h5>
        <input v-model="duree" type="number" min="0" max="100"/>
        <select class="unite" type="text" v-model="dureeUnite" />
      </div>
      <div>
        <h5>Fréquence</h5>
        <input v-model="frequence" type="number" min="0" max="10" />
        <h5>fois par</h5>
        <select class="unite" type="text" v-model="frequenceUnite" />
      </div>
      <div>
        <h5>Quantité</h5>
        <input v-model="quantite" type="number" step=".5" min="0" max="15" />
        <h5> dose(s) par prise</h5>
       
      </div>

      <input id="valider" type="submit" value="ajouter" />
    </form>
  </div>
</template>
<style scopped>
</style>