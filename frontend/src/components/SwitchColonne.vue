<template>
    <div class="form-check form-switch">
        <label class="form-check-label" :for="`Switch${attribut}`">{{ nomAffichage }}</label>
        <input class="form-check-input" type="checkbox" role="switch" :id="`Switch${attribut}`"
            @click="cacherAfficherColonne(attribut)" checked />
    </div>
</template>

<script setup>
defineProps(["attribut", "nomAffichage", "checked"]);

function cacherAfficherColonne(attribut) {
    
    var el = document.getElementsByClassName(attribut);
    for (var i = 0; i < el.length; i++) {
        if (el[i].classList.contains("d-none")) {
            el[i].classList.remove("d-none");
        } else {
            el[i].classList.add("d-none");
        }
    }
}
</script>