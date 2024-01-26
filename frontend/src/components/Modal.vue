<template>
  <div>
    <div
      v-if="this.$store.state.openModal"
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
          <div class="modal-footer justify-content-between">
            <button
              type="button"
              class="btn btn-default"
              data-dismiss="modal"
              @click="closeModal"
            >
              Close
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="callbackSuccess"
            >
              {{ labelSuccess }}
            </button>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
    <div
      v-if="this.$store.state.openModal"
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
  },
  data() {},
  methods: {
    closeModal() {
      this.$store.commit("openModal", false);
    },
  },
};
</script>
<style></style>
