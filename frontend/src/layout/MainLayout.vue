<template>
  <loading
    v-model:active="$store.state.isLoading"
    :can-cancel="false"
    :is-full-page="true"
    background-color="#000"
    color="#00f"
  />
  <div class="wrapper">
    <NavBar></NavBar>

    <SideBar></SideBar>
    <div class="content-wrapper">
      <router-view></router-view>
    </div>

    <footer class="main-footer">
      <div class="float-right d-none d-sm-block"><b>Version</b> 3.2.0</div>
      <strong
        >Copyright &copy; 2014-2021
        <a href="https://adminlte.io">AdminLTE.io</a>.</strong
      >
      All rights reserved.
    </footer>

    <aside class="control-sidebar control-sidebar-dark"></aside>
  </div>
</template>

<script>
import NavBar from "./inc/NavBar";
import SideBar from "./inc/SideBar";
import ContentHeader from "@/components/ContentHeader";
import Loading from "vue-loading-overlay";
import "vue-loading-overlay/dist/css/index.css";
export default {
  name: "MainLayout",
  data() {},
  components: {
    NavBar,
    SideBar,
    ContentHeader,
    Loading,
  },
};
</script>
