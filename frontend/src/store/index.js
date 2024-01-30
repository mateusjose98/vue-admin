import { createStore } from "vuex";

import AuthService from "@/services/AuthService";
export default createStore({
  state: {
    app: {
      name: "Gestão",
    },
    user: {
      name: "José Mateus",
    },
    isLoading: false,
    modal: { openModal: false, id: null },
  },
  getters: {},
  mutations: {
    openModal(state, payload) {
      if (payload) {
        state.modal = payload;
      }
    },
    toggleLoading(state, payload) {
      if (payload) {
        state.isLoading = payload;
      } else {
        state.isLoading = false;
      }
    },
    setToken(state, token) {
      state.token = token;
      Cookies.set("token", token, { expires: 7 });
    },
    clearToken(state) {
      state.token = null;
      Cookies.remove("token");
    },
  },
  actions: {
    login({ commit }, { username, password }) {
      new AuthService()
        .login({ username, password })
        .then((r) => {
          console.log(r.accessToken);
          const token = r.accessToken;
          commit("setToken", token);
        })
        .catch((e) => null);
    },
  },
});
