<template>
  <div class="container mt-3">
    <form @submit.prevent="addCountry">
      <div class="mb-3 mt-3">
        <label for="code" class="form-label">Code:</label>
        <input class="form-control" v-model="data.country.code" />
      </div>
      <div class="mb-3">
        <label for="name" class="form-label">Name:</label>
        <input class="form-control" v-model="data.country.name" />
      </div>
      <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";


const emptyCountry = {
  id: "",
  code: "",
  name: "",
};

const data = reactive({
  country: {...emptyCountry},
});

defineExpose({
  data,
})


const emit = defineEmits(['countryAdded',])

function addCountry() {
  const options = {
    method: "POST",
    mode: "cors",
    body: JSON.stringify(data.country),
    headers: {
      "Content-Type": "application/json",
    },
  };

  fetch("api/countries", options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.country = {...emptyCountry}; // On réinitialise le formulaire
      emit('countryAdded', json); // On notifie le parent que le pays a été ajouté
    })
    .catch((error) => alert(error));
}
</script>