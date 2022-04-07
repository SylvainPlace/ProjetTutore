<template>
  <ListeMaladie4/>
  <maladies-list v-bind:maladies="data.toutesMaladies" />
  <AddPatient />
  <div class="container py-4">
    <DetailPatient />
  </div>
</template>

<script setup>
import ListeMaladie4 from "@/components/ListeMaladie4.vue";

import AddPatient from "@/components/AddPatient.vue";
import DetailPatient from "@/components/DetailPatient.vue";
</script>
