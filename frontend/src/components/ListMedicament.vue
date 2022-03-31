<script setup>
import { reactive, onMounted } from "vue";
defineProps(["patientChoisi"]);

const data = reactive({
    
});

function choixPatient(patient) {
    data.patientChoisi = patient;
    console.log(data.patientChoisi);
}

</script>

<template>
  
</template>
