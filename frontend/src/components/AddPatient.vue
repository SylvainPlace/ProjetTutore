<template>
  <h3>Patient à ajouter</h3>

  <form
    @submit.prevent="
      $emit('addPatient', nom, prenom, adresse_mail, mdp, date_de_naiss)
    "
  >
    <input type="text" v-model="nom" placeholder="Nom ?" />
    <input type="text" v-model="prenom" placeholder="Prenom ?" />
    <input type="text" v-model="adresse_mail" placeholder="Adresse email ?" />
    <input type="text" v-model="mdp" placeholder="Mots de passe ?" />
    <input
      type="text"
      v-model="date_de_naiss"
      placeholder="Date de naissance ?"
    />
    <select>
      <option>Patient</option>
      <option>Infirmier</option>
      <option>Administrateur</option>
      <option>Médecin</option>
      <option>Pharmacien</option>
    </select>
    <input type="submit" value="Ajouter un patient" />
  </form>
</template>

<script setup>
</script>