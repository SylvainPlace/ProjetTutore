<template>
  <div>
    <img src="@/assets/logo.png" alt="logo" />
    
  </div>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <router-link to="/" class="navbar-brand">Accueil</router-link>
    <router-link to="/personnelSoignant" class="navbar-brand"
      >Infirmier</router-link
    >
    <router-link to="/ajoutTraitement" class="navbar-brand"
      >Ajout Traitement</router-link
    >
    <router-link to="/resultat" class="navbar-brand">Résultat</router-link>
  </nav>
  <router-view />
</template>

<script setup>
defineProps(["nom", "prenom"]);
</script>

<style scoped>
img {
  max-width: 100%;
  height: 100px;
}
</style>