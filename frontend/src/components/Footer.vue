

<script setup>
import { reactive, ref } from "vue";
let remerciement = ref("oui");
function pouce() {
    console.log("hey");
    remerciement = remerciement+"Merci pour ton avis !";
    return remerciement;
}
</script>


<template>
    <footer>
        <div id="vide">
            <div id="leFooter" class="row row-cols-3 py-3 my-3 border-top">

                <div id="aimer">
                    <p>Vous aimez ?</p>
                    <img src="@/assets/pouceVert.png" alt="Bouton pouce vert" height="50" width="50" @click="pouce()"
                        id="img1" />
                    <img src="@/assets/pouceRouge.png" alt="Bouton pouce rouge" height="45" width="45"
                        @click="pouce()" />
                    <p>hfg {{ remerciement }}</p>
                </div>

                <div id="contact">
                    <!--<p>Five Cure</p>-->
                    <p>projet <img src="@/assets/logo_blanc.png" alt="logo FiveCure" height="60" width="110" />
                        de la Team <img src="@/assets/PentaPathologie_blanc.png" alt="logo PentaPathologie" height="70"
                            width="190" /> </p>
                    <!--<p>Polypathologique</p>-->
                    <p>Océane Chabus | Florian Chavernac | Mouric Manon | Sylvain Place | Raynal Mathilde</p>
                    <p> <img src="@/assets/logo_isis_blanc.png" alt="logo isis" height="20" width="50" /> École
                        d'ingénieurs, Campus Universitaire</p>
                </div>


                <!-- Bouton -->
                <!--<input type="image" id="image" value="Revenir en haut de page" src="@/assets/flechePtut.png" alt="Fleche sur laquelle cliquer pour revenir au haut de page "/>-->

                <div id="haut">
                    <img src="@/assets/flechePtut.png" alt="Fleche sur laquelle cliquer pour revenir au haut de page"
                        height="50" width="50" />
                    <a href="#top" title="ancre vers le haut de la page">Revenir en haut de page</a>
                </div>


            </div>

        </div>
    </footer>
</template>



<style>
#leFooter {
    bottom: 0;
    width: 100%;
    padding-top: 20px;
    height: 20px;

    background: #ff5f6d;
    background: -webkit-linear-gradient(to right, #2dafd6, #03619f);
    background: linear-gradient(to right, #03619f, #2dafd6);
    min-height: 15vh;

    display: flex;
    position: fixed;

    line-height: 0.9em;
}

#vide {
    bottom: 0;
    width: 100%;
    height: 50px;
    min-height: 20vh;
}

#aimer {
    color: white;
    /*marron foncé color: #9f4850;*/
}

#img1:visited {
    color: #03619f;

}

#contact {
    color: white;
}

a:link {
    text-decoration: none;
}

a:visited {
    color: white;
}
</style>
