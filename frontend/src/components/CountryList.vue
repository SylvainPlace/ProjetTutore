<template>
  <div class="container mt-3">
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Code</th>
          <th>Nom</th>
          <th>Action</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="country in data.countries" :key="country.id">
          <td>{{ country.code }}</td>
          <td>{{ country.name }}</td>
          <td>
            <button
              class="btn btn-sm btn-outline-danger"
              @click="deleteCountry(country)"
            >
              Supprimer
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { reactive, onMounted } from "vue";

let data = reactive({
  countries: [],
});

defineExpose({ // On expose la méthode 'refresh' pour être utilisée par le parent
  refresh,
})

// On définit les événements générés par le composant
const emit = defineEmits(['countryEdited',])

function editCountry(country) {
  emit('countryEdited', country); // On notifie le parent qu'il faut modifier le pays
}

function deleteCountry(country) {
  console.log(country);
  const options = {
    method: "DELETE",
  };
  fetch(country._links.self.href, options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      refresh();
    })
    .catch((error) => alert(error));  
}


function refresh() {
  fetch("api/countries")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.countries = json._embedded.countries;
    })
    .catch((error) => alert(error));
}

onMounted(refresh);
</script>
