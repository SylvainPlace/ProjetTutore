<template>
<div>
<img src="@/assets/logo.png" alt="logo">
<h3>Bienvenue {{prenom }} {{nom}}</h3>
</div>

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