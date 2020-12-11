import axios from "axios";

export default {
  namespaced: true,
  state: {
    token: null,
    user: null,
  },
  getters: {
    authenticated(state) {
      return state.token && state.user;
    },
    user(state) {
      return state.user;
    },
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_USER(state, user) {
      state.user = user;
    },
  },
  actions: {
    async login({ dispatch }, credentials) {
      return await axios.post("/user/login", credentials).then((response) => {
        return dispatch("attempt", response.data.token);
      });
    },

    async register(_, credentials) {
      return await axios.post("/user/register", credentials);
    },

    async attempt({ commit, state }, token) {
      if (token) {
        commit("SET_TOKEN", token);
      }

      if (!state.token) {
        return;
      }

      commit("SET_TOKEN", token);

      try {
        return await axios.get("/user/me").then((response) => {
          return commit("SET_USER", response.data);
        });
      } catch (e) {
        commit("SET_TOKEN", null);
        commit("SET_USER", null);
        throw e;
      }
    },

    logout({ commit }) {
      return axios.post("/user/logout").then(() => {
        commit("SET_TOKEN", null);
        commit("SET_USER", null);
      });
    },
  },
};
