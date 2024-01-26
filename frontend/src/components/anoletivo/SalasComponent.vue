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
            </tr>
          </thead>

          <tbody>
            <tr v-for="(p, i) in salas">
              <td>{{ i + 1 }}</td>
              <td>{{ p.nome }}</td>

              <td>
                <span class="badge badge badge-primary m-1"
                  >Ar condicionado</span
                >
                <span class="badge badge badge-primary m-1">Kit Projetor</span>
                <span class="badge badge badge-primary m-1"
                  >Quadro digital</span
                >
              </td>
              <td>{{ p.capacidade }} pessoas</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <Modal labelSuccess="Salvar" titulo="Nova sala">
      <form>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <label for="nome">Nome</label>
              <input
                type="text"
                class="form-control"
                id="nome"
                placeholder="Digite o nome ..."
              />
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="cpf">Recursos</label>
              <div class="custom-control custom-checkbox">
                <input
                  class="custom-control-input"
                  type="checkbox"
                  id="customCheckbox2"
                />
                <label for="customCheckbox2" class="custom-control-label"
                  >Ar condicionado</label
                >
              </div>
              <div class="custom-control custom-checkbox">
                <input
                  class="custom-control-input"
                  type="checkbox"
                  id="customCheckbox4"
                />
                <label for="customCheckbox4" class="custom-control-label"
                  >Wifi</label
                >
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
                type="text"
                class="form-control"
                id="cpf"
                placeholder="Digite apenas os números"
              />
            </div>
          </div>
        </div>
      </form>
    </Modal>
  </div>
</template>
<script>
import Modal from "../Modal.vue";
export default {
  props: {
    salas: Array,
  },
  components: {
    Modal,
  },
  methods: {
    abrirModal() {
      this.$store.commit("openModal", true);
    },
  },
};
</script>
<style></style>
