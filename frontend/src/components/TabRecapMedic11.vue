<template>
  <div class="container mt-3">
    <table class="table table-bordered table-sm table-hover">
      <thead>
        <tr>
          <th>Liste des médicaments ajoutés</th>
          <th>Actions possibles</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="traitement in listTraitement" :key="traitement.id">
          <td>Nom : {{ traitement.medic }} <br/> 
          <!--Informations sur la prise : {{ traitement.info_prise }} <br/> -->
          <!--Contres indications : {{ traitement.contre_indications }} -->
          </td>
          <td>
            <button
              @click="deleteMedicament(traitement)"
            >
              Supprimer
            </button>
            <button
              @click="modifMedicament(traitement)"
            >
              Modifier
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { reactive, onMounted, ref } from "vue";

import traitement from "@/Traitement.js";
const listTraitement = reactive([]);

let data = reactive({
  medicaments: [],
});

defineExpose({ // On expose la méthode 'refresh' pour être utilisée par le parent
  refresh,
})

// On définit les événements générés par le composant
const emit = defineEmits(['medicamentEdited',])

function editMedicament(medicament) {
  emit('medicamentEdited', medicament); // On notifie le parent qu'il faut modifier le pays
}

function deleteMedicament(medicament) {
    console.log(medicament._links.self.href);
  console.log(medicament);
  const options = {
    method: "DELETE",
  };
  fetch(medicament._links.self.href, options)
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      refresh();
    })
    .catch((error) => alert(error));  
}


function refresh() {
  fetch("api/medicaments")
    .then((response) => {
      if (!response.ok) { // status != 2XX
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.medicaments = json._embedded.medicaments;
    })
    .catch((error) => alert(error));
}

onMounted(refresh);
</script>
