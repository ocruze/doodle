import "@babel/polyfill";
import "mutationobserver-shim";
import Vue from "vue";
import "./plugins/bootstrap-vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import axios from "axios";

/* font awesome */
import { library } from "@fortawesome/fontawesome-svg-core";
import {
  faSignInAlt,
  faSignOutAlt,
  faUserPlus,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

library.add(faSignInAlt, faSignOutAlt, faUserPlus);
Vue.component("font-awesome-icon", FontAwesomeIcon);
/* font awesome */

require("@/store/subscriber");

Vue.config.productionTip = false;

axios.defaults.baseURL = "http://localhost:8080/api";

store.dispatch("auth/attempt", localStorage.getItem("token")).then(() => {
  new Vue({
    router,
    store,
    render: (h) => h(App),
  }).$mount("#app");
});
