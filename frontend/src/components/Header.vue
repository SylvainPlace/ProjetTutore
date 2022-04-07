<template>
  <div>
    <img src="@/assets/logo.png" alt="logo" />
    <h3>Bienvenue {{ prenom }} {{ nom }}</h3>
  </div>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <router-link to="/" class="navbar-brand">Accueil</router-link>
    <router-link to="/personnelSoignant" class="navbar-brand"
      >Infirmier</router-link
    >
    <router-link to="/ajoutTraitement" class="navbar-brand"
      >Ajout Medicament</router-link
    >
    <router-link to="/resultat" class="navbar-brand">Resultat</router-link>
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