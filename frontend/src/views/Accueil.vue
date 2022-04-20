<template>
  
  <h1>Bienvenue sur l'application WEB de FiveCure !</h1>

  <p>Ce site WEB vous permettra de gérer les ordonnaces mieux que quiconque. <br> Une prise en main facilitée expliquée en quelques images </p>
  <video controls poster="video.jpg">
    <source src="@/assets/video.mp4"/>
  </video>
    <Footer/>

</template>

<script setup>
// @ is an alias to /src

import Footer from "@/components/Footer.vue"

</script>
<style scoped>
 h1{
   font-family: Avenir, Helvetica, Arial, sans-serif;
   color :white;
   background-color: #B48B75;
   width: 832px;
   position :absolute;
   top: 242px;
   left: 265px;
 }

 p{
 font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 18px;
  color:black;
  position :absolute;
  top: 302px;
  left: 265px;
 }

 video{
 width : 450px;
 position :absolute;
 top: 396px;
 left: 466px;
 }
</style>
