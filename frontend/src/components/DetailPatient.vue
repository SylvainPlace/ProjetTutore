<script setup>
import { reactive } from "vue";
import SelecteurPatient from './SelecteurPatient.vue';
import ListMedicament from './ListMedicament.vue';

const data = reactive({
    patientChoisi: "",
});

function choixPatient(patient) {
    data.patientChoisi = patient;
    console.log(data.patientChoisi);
}

function fetchPatients() {
    fetch("api/utilisateurs")
        .then((response) => response.json())
        .then((json) => {
            data.allPatients = json._embedded.utilisateurs;
        })
        .catch((error) => alert(error));
}

</script>

<template>
    <SelecteurPatient @patientEvent="choixPatient" />
    <div v-if="data.patientChoisi != ''">
        <ListMedicament :patientChoisi="patientChoisi" />
    </div>
</template>
