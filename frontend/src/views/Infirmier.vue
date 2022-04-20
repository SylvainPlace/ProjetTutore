<template>
  <div class="container espace">
    <AddPatient />
    <DetailPatient />
    <DetailMaladie />
  </div>
</template>

<script setup>
import AddPatient from "@/components/AddPatient.vue";
import DetailPatient from "@/components/DetailPatient.vue";
import DetailMaladie from "../components/DetailMaladie.vue";
</script>
