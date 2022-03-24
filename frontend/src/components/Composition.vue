<script setup>
// variable
const msg = "Hello !!!";

// functions
function log() {
  console.log(msg);
}
</script>

<template>
  <div @click="log">{{ msg }}</div>
</template>
