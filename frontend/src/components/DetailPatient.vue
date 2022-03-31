<script setup>
import { reactive } from "vue";
import SelecteurPatient from './SelecteurPatient.vue';
import ListMedicament from './ListMedicament.vue';

var patientChoisi = reactive("zdzd");

function choixPatient(patient) {
    patientChoisi = patient;
    console.log(patientChoisi);
    return patientChoisi;
}


</script>

<template>
    <SelecteurPatient @patientEvent="choixPatient" />
    <ListMedicament :patientChoisi="patientChoisi" />
</template>
