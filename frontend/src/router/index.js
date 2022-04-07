import { createRouter, createWebHistory } from "vue-router";
import Accueil from "../views/Accueil.vue";
import Infirmier from "../views/Infirmier.vue";
import Medicament from "../views/Medicament.vue";
import Resultat from "../views/Resultat.vue";

const routes = [{
        path: "/",
        name: "Accueil",
        component: Accueil,
    },
    {
        path: "/personnelSoignant",
        name: "Infirmier",
        component: Infirmier,
    },
    {
        path: "/AjoutTraitement",
        name: "Ajout Traitement",
        component: Medicament,
    },
    {
        path: "/resultat",
        name: "Resultat",
        component: Resultat,
    },

];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

export default router;