<template>
  <li class="nav-item" :class="anyActive() ? 'menu-is-opening menu-open' : ''">
    <a href="#" class="nav-link">
      <i class="nav-icon fas fa-tree"></i>
      <p><slot></slot></p>
      <p>
        <i class="fas fa-angle-left right"></i>
      </p>
    </a>
    <ul
      class="nav nav-treeview"
      :style="anyActive() ? { display: 'block' } : { display: 'none' }"
    >
      <li class="nav-item" v-for="link in links">
        <router-link
          class="nav-link"
          :to="link.url"
          :class="{ active: isActive(link.url) }"
        >
          <i class="far fa-circle nav-icon"></i>
          <p>{{ link.name }}</p>
        </router-link>
      </li>
    </ul>
  </li>
</template>

<script>
export default {
  props: {
    icon: {
      type: String,
      default: null,
    },
    link: {
      type: String,
      default: null,
    },
    links: {
      type: Array,
    },
  },
  methods: {
    isActive(rota) {
      rota = "/" + rota;
      return this.$route.path === rota;
    },
    anyActive() {
      for (let r of this.links) {
        if (this.isActive(r.url)) {
          return true;
        }
      }
      return false;
    },
  },
};
</script>
