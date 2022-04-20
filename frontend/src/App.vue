<script setup>
import Footer from "@/components/Footer.vue";
import Header from "@/components/Header.vue";
</script>

<template>
  <div>
    <Header />
    <Footer />
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* Curseur indiquant un clic possible*/
.pointer {
  cursor: pointer;
}

/* Curseur classique*/
.defaultCursor {
  cursor: default;
}

.espace {
  min-height: 70vh;
}
</style>
