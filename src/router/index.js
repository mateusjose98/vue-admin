import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutView from "../views/AboutView.vue";
import LoginView from "../views/LoginView.vue";
import MainLayout from "../layout/MainLayout.vue";
import SearchStudent from "../views/SearchStudent.vue";
import CreateStudent from "../views/CreateStudent.vue";
const routes = [
  {
    path: "/",
    component: MainLayout,
    children: [
      {
        path: "",
        component: HomeView,
      },
      {
        path: "sobre",
        component: AboutView,
      },
      {
        path: "pesquisa-aluno",
        component: SearchStudent,
      },
      {
        path: "cadastro-aluno",
        component: CreateStudent,
      },
    ],
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
