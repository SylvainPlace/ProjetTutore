<script setup>
import { reactive } from "vue";
import SelecteurMaladie from './SelecteurMaladie.vue';


const data = reactive({
    maladieChoisi: "",
  
});

function choixMaladie(idMaladie, allMaladies) {
  for(let maladie of allMaladies){
    if(idMaladie == maladie.id){
      data.maladieChoisi = maladie;
    }
  }
    console.log(data.maladieChoisiId);
}
</script>

<template>
    <h3>Maladie(s) du patient</h3>
    <SelecteurMaladie @maladieEvent="choixMaladie" />
    <div>
    <table class="table table-bordered table-sm table-hover" >
      <thead class="head-table">
        <tr>
          <th>Nom maladie</th>
          <th>Symptômes</th>
          <th>Description</th>
          <th>Facteur aggravant</th>
          <th>CIM-10</th>            
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>{{ data.maladieChoisi.nom_maladie }}</td>
          <td>{{ data.maladieChoisi.symptomes }}</td>
          <td>{{ data.maladieChoisi.description }}</td>
          <td>{{ data.maladieChoisi.facteur_aggravant }}</td>
          <td>{{ data.maladieChoisi.cim_10 }}</td>
        </tr>
      </tbody>
    
    </table>
  </div>
</template>