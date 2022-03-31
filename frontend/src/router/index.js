import { createRouter, createWebHistory } from "vue-router";
import Accueil from "src/views/Accueil.vue";
import Infirmier from "src/views/Infirmier.vue";
import Medicament from "src/views/Medicament.vue";
import Resultat from "src/views/Resultat.vue";

const routes = [
  {
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
    path: "/ajoutMedicament",
    name: "AjoutMedicament",
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
