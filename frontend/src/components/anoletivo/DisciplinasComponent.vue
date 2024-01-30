<template>
  <div class="card card-primary card-outline">
    <div class="card-header">
      <h2 class="card-title font-weight-bold">Disciplinas</h2>
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
            <tr v-for="(p, i) in disciplinas">
              <td>{{ i + 1 }}</td>
              <td>{{ p.nome }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="card-footer"></div>
    <Modal id="disciplinas" labelSuccess="Salvar" titulo="Nova disciplina">
      <form @submit.prevent="salvarDisciplina">
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input
                type="text"
                class="form-control"
                id="nome"
                placeholder="Digite o nome ..."
                v-model="disciplina.nome"
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
import TurmaService from "@/services/TurmaService";
import Modal from "@/components/Modal.vue";

export default {
  props: {
    disciplinas: Array,
  },
  data() {
    return {
      disciplina: {
        nome: null,
        id: null,
      },
    };
  },
  methods: {
    salvarDisciplina() {
      new TurmaService()
        .criarDisciplina(this.disciplina)
        .then((response) => {
          this.$emit("disciplinaSalva");
          this.limparForm();
          Toast.fire({
            icon: "success",
            title: "Disciplina criada com sucesso!",
          });
        })
        .finally(() => {
          this.fecharModal();
          this.$store.commit("toggleLoading", false);
        });
    },
    abrirModal() {
      this.$store.commit("openModal", { openModal: true, id: "disciplinas" });
    },
    fecharModal() {
      this.limparForm();
      this.$store.commit("openModal", { openModal: false, id: "disciplinas" });
    },
    limparForm() {
      this.sala = {
        nome: null,
        id: null,
      };
    },
  },
  components: {
    Modal,
  },
};
</script>
<style></style>
