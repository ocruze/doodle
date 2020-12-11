import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Dashboard from "../views/Dashboard.vue";
import About from "../views/About.vue";
import Login from "../views/Auth/Login.vue";
import Register from "../views/Auth/Register.vue";
import NewDoodle from "../views/NewDoodle.vue";
import Recover from "../views/Auth/Recover.vue";
import DoodlePage from "../views/DoodlePage.vue";
import store from "@/store";
import NotFound from "@/views/NotFound.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: Dashboard,
  },
  {
    path: "/about",
    name: "About",
    component: About,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/recover",
    name: "Recover",
    component: Recover,
  },
  {
    path: "/new-doodle",
    name: "NewDoodle",
    component: NewDoodle,
  },
  {
    path: "/doodle/:idDoodle",
    name: "DoodlePage",
    component: DoodlePage,
  },
  {
    path: "*",
    name: "NotFound",
    component: NotFound,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

const openRoutes = [
  "Home",
  "Login",
  "Register",
  "Recover",
  "About",
  "NotFound",
];
const authRoutes = ["Login", "Register", "Recover"];

router.beforeEach((to, from, next) => {
  if (authRoutes.includes(to.name)) {
    if (store.getters["auth/authenticated"]) {
      next({ name: "Dashboard" });
    } else {
      next();
    }
  }

  if (openRoutes.includes(to.name)) {
    next();
  } else if (store.getters["auth/authenticated"]) {
    next();
  } else {
    Vue.$toast.info("Please log in to continue");
    next({ name: "Login" });
  }
});

export default router;
