import { createStore } from "vuex";

export default createStore({
  state: {
    app: {
      name: "Gestão",
    },
    user: {
      name: "José Mateus",
    },
    isLoading: false,
  },
  getters: {},
  mutations: {
    toggleLoading(state, payload) {
      if (payload) {
        state.isLoading = payload;
      } else {
        state.isLoading = false;
      }
    },
  },
  actions: {},
});
