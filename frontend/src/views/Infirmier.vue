<template>
  <AddPatient />
  <div class="container py-4">
    <DetailPatient />
  </div> 
  <DetailMedic/>
  
</template>

<script setup>

import AddPatient from "@/components/AddPatient.vue";
import DetailPatient from "@/components/DetailPatient.vue";
import DetailMedic from "../components/DetailMaladie.vue";
</script>
