<template>
  <div class="login-box">
    <div class="login-logo">
      {{ $store.state.app.name }}
    </div>
    <!-- /.login-logo -->
    <div class="card">
      <div class="card-body login-card-body">
        <p class="login-box-msg">Faça o login para iniciar uma sessão</p>

        <form method="post" @submit.prevent="login">
          <div class="input-group mb-3">
            <input
              v-model="username"
              type="text"
              class="form-control"
              placeholder="00000000000"
            />
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-envelope"></span>
              </div>
            </div>
          </div>
          <div class="input-group mb-3">
            <input
              type="password"
              class="form-control"
              placeholder="Password"
              v-model="password"
            />
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-lock"></span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-8">
              <!-- <div class="icheck-primary">
                <input type="checkbox" id="remember" />
                <label for="remember"> Remember Me </label>
              </div> -->
            </div>
            <!-- /.col -->
            <div class="col-4">
              <button type="submit" class="btn btn-primary btn-block">
                Sign In
              </button>
            </div>
            <!-- /.col -->
          </div>
        </form>

        <p class="mb-1">
          <a href="forgot-password.html">Esqueci minha senha</a>
        </p>
      </div>
      <pre>{{ $store.state.token }}</pre>
      <!-- /.login-card-body -->
    </div>
  </div>
</template>
<script>
import AuthService from "../services/AuthService";

export default {
  data() {
    return {
      username: null,
      password: null,
    };
  },
  methods: {
    async login() {
      try {
        await this.$store.dispatch("login", {
          username: this.username,
          password: this.password,
        });
      } catch (error) {
        console.error("Erro durante o login:", error);
      }
    },
  },
  beforeMount() {
    $("body").removeClass("sidebar-mini").addClass("login-page");
    $("title").html(`Login`);
  },
};
</script>
