import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Countries from "@/views/Countries.vue";
import CountryEdit from "@/views/CountryEdit.vue";
import CityEdit from "@/views/CityEdit.vue";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/countries",
    name: "Countries",
    component: Countries,
  },
  {
    path: "/cityEdit",
    name: "CityEdit",
    component: CityEdit,
  },
  {
    path: "/countryEdit",
    name: "CountryEdit",
    component: CountryEdit,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
