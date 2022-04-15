<script setup>
import Footer from "@/components/Footer.vue";
import Header from "@/components/Header.vue";
</script>




<template>
  <div>
    <Header nom="Dupont" prenom="Jean" />
    <Footer />
  </div>
</template>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 1.1rem;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}


/* Curseur indiquant un clic possible*/
.pointer {
  cursor: pointer;
}

/* Curseur classique*/
.defaultCursor {
  cursor: default;
}
</style>
