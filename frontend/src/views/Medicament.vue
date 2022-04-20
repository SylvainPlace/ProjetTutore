<script setup>
import AjoutTraitement from '@/components/AjoutTraitement.vue';
import CountryList from "@/components/CountryList.vue";
import TabRecapMedic11 from "@/components/TabRecapMedic11.vue"
/*function handlerAjouterTraitement(medic, dureeUnite, duree, quantite,frequence,frequenceUnite) {
  if (quantite <= 0) {
  } else {
    let url = "/api/soigners";
    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    const fetchOptions = {
      method: "POST",
      headers: myHeaders,
      body: JSON.stringify({ nom: nomProduit, qte: quantite }),
    };

    fetch(url, fetchOptions)
      .then((response) => {
        return response.json();
      })

      .then((dataJSON) => {
        getIngredient();
      })
      .catch((error) => {
        console.log(error);
      });
  }
}*/
</script>
<template>
  

<AjoutTraitement/>



</template>
