<template>
  <div>
    <div
      v-if="
        this.$store.state.modal.openModal &&
        this.id === this.$store.state.modal.id
      "
      class="modal fade show"
      id="modal-lg"
      style="display: block"
    >
      <div class="modal-dialog modal-md">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">{{ titulo }}</h4>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true" @click="closeModal">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <slot></slot>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
    <div
      v-if="
        this.$store.state.modal.openModal &&
        this.id === this.$store.state.modal.id
      "
      class="modal-backdrop fade show"
    ></div>
  </div>
</template>
<script>
export default {
  name: "Modal",
  props: {
    objeto: Object,
    titulo: String,
    labelSuccess: String,
    id: String,
  },
  data() {},
  methods: {
    closeModal() {
      this.$store.commit("openModal", {
        openModal: false,
        id: this.id,
      });
    },
  },
};
</script>
<style></style>
