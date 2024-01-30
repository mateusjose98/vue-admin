<template>
  <div class="card card-primary card-outline">
    <div class="card-header">
      <h2 class="card-title font-weight-bold">Séries</h2>
      <div>
        <button @click="abrirModal" class="btn btn-info btn-sm float-right">
          <i class="fa fa-plus"></i> Adicionar
        </button>
      </div>
    </div>
    <div class="card-body">
      <div class="table-responsive p-0" style="height: 300px">
        <table class="table table-head-fixed table-hover text-nowrap">
          <thead>
            <tr>
              <th>ID</th>
              <th>nome</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="(p, i) in series">
              <td>{{ i + 1 }}</td>
              <td>{{ p.nome }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <Modal id="series" labelSuccess="Salvar" titulo="Nova série">
      <form @submit.prevent="salvarSerie">
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input
                type="text"
                class="form-control"
                id="nome"
                placeholder="Digite o nome ..."
                v-model="serie.nome"
              />
            </div>
          </div>
        </div>
        <div class="d-flex justify-content-between">
          <button type="submit" class="btn btn-primary">Salvar</button>
          <button type="button" class="btn btn-default" @click="fecharModal">
            Fechar
          </button>
        </div>
      </form>
    </Modal>
  </div>
</template>
<script>
import Modal from "../Modal.vue";
import TurmaService from "@/services/TurmaService";
export default {
  props: {
    series: Array,
  },
  data() {
    return {
      serie: {
        id: null,
        nome: null,
      },
    };
  },
  methods: {
    salvarSerie() {
      this.$store.commit("toggleLoading", true);

      new TurmaService()
        .criarSerie(this.serie)
        .then(() => {
          this.serie = {};
          this.$emit("serieCriada");

          Toast.fire({
            icon: "success",
            title: "Série criada com sucesso!",
          });
        })
        .finally(() => {
          this.fecharModal();
          this.$store.commit("toggleLoading", false);
        });
    },
    abrirModal() {
      this.$store.commit("openModal", { openModal: true, id: "series" });
    },
    fecharModal() {
      this.limparForm();
      this.$store.commit("openModal", { openModal: false, id: "series" });
    },
    limparForm() {
      this.serie = {
        id: null,
        nome: null,
      };
    },
  },
  components: { Modal },
};
</script>
<style></style>
