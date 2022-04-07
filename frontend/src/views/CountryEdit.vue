<template>
  <div class="home">
    <h2>Edition d'un pays</h2>
    <div class="row">
      <div class="col">
        <CountryForm @countryAdded="added" ref="formulaire"/>
      </div>
      <div class="col">
        <CountryList @countryEdited="edited" ref="liste" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
// @ is an alias to /src
import CountryForm from "@/components/CountryForm.vue";
import CountryList from "@/components/CountryList.vue";
// Référence vers la liste des pays
const liste = ref(null);

const formulaire = ref(null);

function added(country) {
    liste.value.refresh();    
}

function edited(country) {
    formulaire.value.data.country = {...country};
}
</script>
