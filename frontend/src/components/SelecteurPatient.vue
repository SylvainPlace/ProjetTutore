<script setup>
import { reactive, onMounted } from "vue";

// variables

const data = reactive({
    allPatients: [],
});

const emit = defineEmits(['patientEvent'])

// functions
onMounted(() => {
    fetchPatients(); // On récupère les patients
});

function fetchPatients() {
    fetch("api/utilisateurs")
        .then((response) => response.json())
        .then((json) => {
            data.allPatients = json._embedded.utilisateurs;
            calculDate()
        })
        .catch((error) => alert(error));
}


function calculDate() {
    for (let s of data.allPatients) {
        let date = new Date(s.date_de_naiss);
        s.date_de_naiss = date.toLocaleDateString();
    }
}

</script>

<template>
    <div class="container py-3">
        <label for="patientSelect">Choix du patient :</label>
        <select id="patientSelect" @change="$emit(`patientEvent`, $event.target.value)">
            <option disabled selected>Choisissez un patient</option>
            <option
                v-for="patient in data.allPatients"
                :value="patient.id"
            >{{ patient.prenom }} {{ patient.nom }} né(e) le {{ patient.date_de_naiss }}</option>
        </select>
    </div>
</template>

<style>
</style>