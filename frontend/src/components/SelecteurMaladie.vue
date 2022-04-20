<script setup>
import { reactive, onMounted } from "vue";
// variables

const data = reactive({
    allMaladies: [],
});
const emit = defineEmits(['maladieEvent'])

// functions
onMounted(() => {
    fetchMaladies(); // On récupère les maladies
});

function fetchMaladies() {
    fetch("/api/maladies")
        .then((response) => response.json())
        .then((json) => {
            data.allMaladies = json._embedded.maladies;
        })
        .catch((error) => alert(error));
}


</script>

<template>
    <div class="container pb-3">
        <label for="maladieSelect">Choix de la maladie :</label>
        <select id="maladieSelect" @change="$emit(`maladieEvent`, $event.target.value, data.allMaladies)">
            <option disabled selected key="-1">Choisissez une maladie</option>
            <option v-for="maladie in data.allMaladies" :value="maladie.id" :key="maladie.id">{{
                maladie.nom_maladie
            }} </option>
        </select>
    </div>
</template>

<style>
</style>