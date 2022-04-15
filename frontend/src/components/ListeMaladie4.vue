<template>
 <div>
    <table class="table table-bordered table-sm table-hover" >
      <thead>
        <tr>
          <th>Nom maladie</th>
          <th>Symptômes</th>
          <th>Description</th>
          <th>Facteur aggravant</th>
          <th>CIM-10</th>            
        </tr>
      </thead>
      <tbody>
        <tr v-for="maladie in data.maladies" :key="maladie.id">
          <td>{{ maladie.nom_maladie }}</td>
          <td>{{ maladie.symptomes }}</td>
          <td>{{ maladie.description }}</td>
          <td>{{ maladie.facteur_aggravant }}</td>
          <td>{{ maladie.cim_10 }}</td>
        </tr>
      </tbody>
    
    </table>
  </div>

</template>

<script setup>
import {reactive, onMounted} from "vue";
let data = reactive({ maladies : [],});
 // Utilise l'API REST auto-générée pour avoir les différentes maladies
 function Maladies() {
  fetch("api/maladies")
    .then((response) => {
      if(!response.ok){
        throw new Error(response.status);
      }
      return response.json();
    })
    .then((json) => {
      data.maladies = json._embedded.maladies;
    })
    .catch((error) => alert(error));
}

onMounted(Maladies);

</script>