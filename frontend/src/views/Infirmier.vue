<template>
  <ListeMaladie4/>
  <maladies-list v-bind:maladies="data.toutesMaladies" />
</template>

<script setup>
import ListeMaladie4 from "@/components/ListeMaladie4.vue";
</script>
