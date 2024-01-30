<template>
  <div class="card card-primary card-outline">
    <div class="card-header">
      <h3 class="card-title font-weight-bold">Salas</h3>
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
              <th>Nome</th>
              <th>Recursos</th>
              <th>Capacidade física</th>
              <th>Ações</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="(p, i) in salas">
              <td>{{ i + 1 }}</td>
              <td>{{ p.nome }}</td>

              <td>
                <span
                  v-for="(r, i) in p.recursos"
                  :key="i"
                  class="badge badge badge-primary m-1"
                  >{{ r }}</span
                >
              </td>
              <td>{{ p.capacidade }} pessoas</td>
              <td>
                <button class="btn btn-primary btn-sm" @click="editarSala(p)">
                  <i class="fa fa-edit"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <Modal id="salas" labelSuccess="Salvar" titulo="Nova sala">
      <form @submit.prevent="salvarSala">
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input
                type="text"
                class="form-control"
                id="nome"
                placeholder="Digite o nome ..."
                v-model="sala.nome"
              />
            </div>
          </div>

          <div class="col-sm-12">
            <div class="form-group">
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  value="Ar Condicionado"
                  id="defaultCheck1"
                  v-model="sala.recursos"
                />
                <label class="form-check-label" for="defaultCheck1">
                  Ar condicionado
                </label>
              </div>
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  value="Wifi"
                  id="defaultCheck2"
                  v-model="sala.recursos"
                />
                <label class="form-check-label" for="defaultCheck2">
                  Wifi
                </label>
              </div>
              <div class="form-check">
                <input
                  class="form-check-input"
                  type="checkbox"
                  value="Quadro digital"
                  id="defaultCheck3"
                  v-model="sala.recursos"
                />
                <label class="form-check-label" for="defaultCheck3">
                  Quadro digital
                </label>
              </div>
            </div>
          </div>

          <div class="col-sm-12">
            <div class="form-group">
              <label for="cpf"
                >Capacidade
                <span class="small"
                  >(capacidade real da sala, sem folgas)</span
                ></label
              >
              <input
                v-model="sala.capacidade"
                type="text"
                class="form-control"
                id="capacidade"
                placeholder="Digite apenas os números"
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
  data() {
    return {
      sala: {
        nome: null,
        capacidade: null,
        recursos: [],
      },
    };
  },
  props: {
    salas: Array,
  },
  components: {
    Modal,
  },
  methods: {
    editarSala(sala) {
      this.sala = sala;
      this.$store.commit("openModal", true);
    },
    salvarSala() {
      this.$store.commit("toggleLoading", true);
      new TurmaService()
        .criarSala(this.sala)
        .then((r) => {
          this.$emit("salaCriada", r);
          Toast.fire({
            icon: "success",
            title: "Sala criada com sucesso!",
          });
        })
        .finally(() => {
          this.fecharModal();
          this.limparForm();
          this.$store.commit("toggleLoading", false);
        });
    },
    abrirModal() {
      this.$store.commit("openModal", { openModal: true, id: "salas" });
    },
    fecharModal() {
      this.limparForm();
      this.$store.commit("openModal", { openModal: false, id: "salas" });
    },
    limparForm() {
      this.sala = {
        nome: null,
        capacidade: null,
        recursos: [],
      };
    },
  },
};
</script>
<style></style>
