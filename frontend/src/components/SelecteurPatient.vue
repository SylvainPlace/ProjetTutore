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
    fetch("api/ListePatient/")
        .then((response) => response.json())
        .then((json) => {
            data.allPatients = json;
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
        <select class="form-select" id="patientSelect" @change="$emit(`patientEvent`, $event.target.value)"
            aria-label="Floating label select example">
            <option selected disabled>Choisissez un patient</option>
            <option v-for="patient in data.allPatients" :value="patient.id">{{ patient.prenom }} {{ patient.nom }} né(e)
                le {{ patient.date_de_naiss }}</option>
        </select>
    </div>
</template>

<style>
</style>