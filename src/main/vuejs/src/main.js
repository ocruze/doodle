import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/bootstrap-vue";
import "./plugins/fontawesome-vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";
import VueToast from "vue-toast-notification";
import "vue-toast-notification/dist/theme-sugar.css";

require("@/store/subscriber");

Vue.config.productionTip = false;
Vue.use(VueToast);

axios.defaults.baseURL = "http://localhost:7070/api";

store.dispatch("auth/attempt", localStorage.getItem("token")).then(() => {
  new Vue({
    router,
    store,
    render: (h) => h(App),
  }).$mount("#app");
});
