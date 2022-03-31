<script setup>
import { reactive, onMounted } from "vue";


defineProps({
    patientChoisi: String
})
defineEmits(['update:patientChoisi'])
const data = reactive({

});



function fetchMedicamentPatient() {
    fetch(patientChoisi + "/soigners")
        .then((response) => response.json())
        .then((json) => {
            console.log(json);
        })
        .catch((error) => alert(error));

}
</script>

<template>
    <p>{{ patientChoisi }}</p>
</template>
