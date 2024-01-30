<template>
  <div class="card card-primary card-outline">
    <div class="card-header">
      <h2 class="card-title font-weight-bold">Turmas</h2>
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
              <th>#</th>
              <th>Descrição</th>
              <th>Ano</th>
              <th>Vagas</th>
              <th>Sala</th>
              <th>Série</th>
              <th>Valor Base</th>
              <th>Ações</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="(p, i) in turmas">
              <td>{{ i + 1 }}</td>
              <td>{{ p.descricao }}</td>
              <td>{{ p.ano }}</td>
              <td>{{ p.vagasDisponiveis }}</td>
              <td>
                {{ p.sala ? p.sala.nome : "" }}
                {{ p.sala ? p.sala.capacidade : "" }}
              </td>
              <td>{{ p.serie ? p.serie.nome : "" }}</td>
              <td>R$ {{ p.valorBase }}</td>
              <td>
                <button
                  @click="desativarTurma(p.id)"
                  class="btn btn-danger btn-sm m-1"
                >
                  <i class="fa fa-ban"></i>
                </button>
                <button
                  @click="editarTurma(p.id)"
                  class="btn btn-primary btn-sm"
                >
                  <i class="fa fa-edit"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="card-footer"></div>
    <Modal id="turmas" labelSuccess="Salvar" titulo="Nova disciplina">
      <form @submit.prevent="salvarTurma">
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <label for="nome">Descrição</label>
              <input
                type="text"
                class="form-control"
                id="nome"
                placeholder="Nome completo ..."
                v-model="turma.descricao"
              />
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="cpf">Ano</label>
              <input
                type="text"
                class="form-control"
                id="cpf"
                placeholder="Digite apenas os números"
                v-model="turma.ano"
              />
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="exampleFormControlSelect1">Sala</label>
              <select
                v-model="turma.sala"
                class="form-control"
                id="exampleFormControlSelect1"
              >
                <option value="">Selecione</option>
                <option v-for="sala in salas" :value="sala">
                  {{ sala.nome }}
                </option>
              </select>
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="cpf"
                >Número de vagas
                <span class="small"
                  >(valor máx. é a quantidade de alunos da sala)</span
                ></label
              >
              <input
                type="text"
                class="form-control"
                id="vagas"
                v-model="turma.vagasDisponiveis"
                placeholder="Digite apenas os números"
              />
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="series">Série</label>
              <select v-model="turma.serie" class="form-control" id="series">
                <option value="">Selecione</option>
                <option v-for="serie in series" :value="serie">
                  {{ serie.nome }}
                </option>
              </select>
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
    turmas: Array,
    salas: Array,
    series: Array,
  },
  data() {
    return {
      turma: {
        descricao: null,
        ano: new Date().getFullYear(),
        vagasDisponiveis: null,
        sala: null,
        serie: null,
        valorBase: null,
        id: null,
      },
    };
  },
  components: {
    Modal,
  },
  methods: {
    async salvarTurma() {
      const service = new TurmaService();
      console.log(this.turma);
      await service.criar(this.turma);
      this.fecharModal();
      this.$emit("turmaSalva");
    },
    async desativarTurma(id) {
      const service = new TurmaService();
      await service.desativarTurma(id);
      this.$emit("turmaSalva");
    },

    abrirModal() {
      this.$store.commit("openModal", { openModal: true, id: "turmas" });
    },
    fecharModal() {
      this.limparForm();
      this.$store.commit("openModal", { openModal: false, id: "turmas" });
    },
    limparForm() {
      this.turma = {
        descricao: null,
        ano: null,
        vagasDisponiveis: null,
        sala: null,
        serie: null,
        valorBase: null,
        id: null,
      };
    },
  },
};
</script>
<style></style>
