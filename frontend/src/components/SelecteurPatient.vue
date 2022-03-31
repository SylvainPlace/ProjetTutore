<script setup>
import { reactive, onMounted } from "vue";

// variables

const data = reactive({
    allPatients: [],
});

const emit = defineEmits(['patientEvent',])

// functions

function fetchPatients() {
    fetch("api/utilisateurs")
        .then((response) => response.json())
        .then((json) => {
            data.allPatients = json._embedded.utilisateurs;
        })
        .catch((error) => alert(error));
}


onMounted(() => {
    fetchPatients(); // On récupère les patients
});
</script>

<template>
<label for="patientSelect">Choix du patient :</label>
    <select
    id="patientSelect"
        @change="$emit(`patientEvent`, $event.target.value)"
        v-model="data.allPatients.id"
    >
        <option value disabled selected>Choisissez un patient</option>
        <option
            v-for="patient in data.allPatients"
            :key="patient.id"
            :value="patient._links.self.href"
        >{{ patient.prenom }} {{ patient.nom }} né(e) le {{ patient.date_de_naiss }}</option>
    </select>
</template>

<style>
</style>