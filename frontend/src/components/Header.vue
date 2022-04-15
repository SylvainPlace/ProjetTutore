<template>
  
    <img src="@/assets/logo.png" alt="logo" />
    
  <div class="navbar mb-3">



    <nav class="container navbar-expand-lg d-flex justify-content-md-between py-3 mb-4 border-bottom">
      <img src="@/assets/logo.png" alt="logo" />
      <router-link to="/" class="navbar-brand"><span class="max">Accueil</span></router-link>
      <router-link to="/personnelSoignant" class="navbar-brand"><span class="max">Infirmier</span></router-link>
      <router-link to="/ajoutTraitement" class="navbar-brand"><span class="max">Ajout Traitement</span></router-link>
      <router-link to="/resultat" class="navbar-brand"><span class="max">Résultat</span></router-link>
      <p class="mt-3">Bienvenue {{ prenom }} {{ nom }}</p>
    </nav>
  </div>
  <router-view />
</template>

<script setup>
defineProps(["nom", "prenom"]);
</script>

<style scoped>
img {
  height: 80px;
}

.navbar {
  background: -webkit-linear-gradient(to right, #2dafd6, #03619f);
  background: linear-gradient(to right, #03619f, #2dafd6);
}

.max{
  font-size: 1.6em;
}
</style>