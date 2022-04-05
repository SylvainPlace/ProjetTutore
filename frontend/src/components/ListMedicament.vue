<script setup>
import { reactive, onMounted, onUpdated } from "vue";


const props = defineProps({
    soignersPatient: {
        type: String,
        required: true,
    },
});


onMounted(() => {
    console.log("first");
    fetchMedicamentPatient(); // On récupère les médicaments
});

function fetchMedicamentPatient() {
    fetch(soignersPatient)
        .then((response) => response.json())
        .then((json) => {
            console.log(json);
        })
        .catch((error) => alert(error));

}
</script>

<template>
    <p>Affiche</p>
    <p>{{ props.soignersPatient }}</p>
</template>
