import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ContentHeader from "./components/ContentHeader.vue";
import Content from "./components/Content.vue";
import Swal from "sweetalert2/dist/sweetalert2.js";
import VueSweetalert2 from "vue-sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
import VueTheMask from "vue-the-mask";

//configuração do Toast
const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  timer: 10000,
  timerProgressBar: true,
  showConfirmButton: false,
});
//salva a configuração global na palavra "Toast"
window.Toast = Toast;
const app = createApp(App);
app.use(VueTheMask);
app.use(VueSweetalert2);

app
  .component("content-header", ContentHeader)
  .component("content", Content)
  .use(store)
  .use(router)
  .mount("#app");
