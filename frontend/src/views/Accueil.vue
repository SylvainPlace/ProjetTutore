<template>
  <div class="home espace">
    <h1 class="mx-auto">Bienvenue sur l'application WEB de FiveCure !</h1>

    <p>Ce site WEB vous permettra de gérer les ordonnaces mieux que quiconque. <br> Une prise en main facilitée
      expliquée en quelques images </p>
    <video controls poster="video.jpg">
      <source src="@/assets/video.mp4" />
    </video>
  </div>
</template>

<style scoped>
h1 {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  color: white;
  width: 842px;
  background-color: #B48B75;
  border-radius: 10px 100px/120px;
}

p {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  font-size: 18px;
  color: black;
}

video {
  width: 600px;
}
</style>
