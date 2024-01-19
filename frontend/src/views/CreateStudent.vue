<template>
  <content-header title="Matricular novo aluno"></content-header>
  <content>
    <div class="row">
      <div class="col-3">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">Etapas</h3>

            <div class="card-tools">
              <button
                type="button"
                class="btn btn-tool"
                data-card-widget="collapse"
              >
                <i class="fas fa-minus"></i>
              </button>
            </div>
          </div>
          <div class="card-body p-0">
            <ul class="nav nav-pills flex-column">
              <li class="nav-item active">
                <a
                  href="#"
                  @click="step = 1"
                  class="nav-link"
                  :class="{ active: step === 1 }"
                >
                  <i class="far fa-address-card"></i> Dados básicos
                  <span class="badge bg-primary float-right">*</span>
                </a>
              </li>
              <li class="nav-item">
                <a
                  href="#"
                  @click="step = 2"
                  :class="{ active: step === 2 }"
                  class="nav-link"
                >
                  <i class="fas fa-user-graduate"></i> Dados escolares
                </a>
              </li>
              <li class="nav-item">
                <a
                  href="#"
                  @click="step = 3"
                  :class="{ active: step === 3 }"
                  class="nav-link"
                >
                  <i class="far fa-money-bill-alt"></i> Financeiro
                </a>
              </li>
            </ul>
          </div>
          <!-- /.card-body -->
        </div>
        <a href="mailbox.html" class="btn btn-success btn-block mb-3"
          >Concluir</a
        >
      </div>

      <div v-show="step === 1" class="col-sm-12 col-md-12 col-lg-9">
        <DefaultCard titulo="Cadastro">
          <FormBasicInfoStudent :aluno="{}" />
        </DefaultCard>
      </div>
      <div v-show="step === 2" class="col-sm-12 col-md-12 col-lg-9">
        <DefaultCard titulo="Cadastro"> TESTE 2 </DefaultCard>
      </div>
      <div v-show="step === 3" class="col-sm-12 col-md-12 col-lg-9">
        <DefaultCard titulo="Cadastro"> TESTE 3 </DefaultCard>
      </div>

      <!-- <div class="col-sm-12 col-md-12 col-lg-12">
        <DefaultCard titulo="Últimos alunos"> <ListStudentView /></DefaultCard>
      </div> -->
    </div>
  </content>
</template>
<script>
import DefaultCard from "@/components/DefaultCard.vue";
import AlunoService from "../services/AlunoService";
import ListStudentView from "./ListStudentView.vue";
import FormBasicInfoStudent from "@/components/FormBasicInfoStudent.vue";

export default {
  data() {
    return {
      studentList: [],
      step: 1,
    };
  },
  created() {
    this.listar();
  },
  methods: {
    async listar() {
      const alunoService = new AlunoService();
      await alunoService
        .getAlunos()
        .then((r) => {
          this.studentList = r;
        })
        .catch(() => {});
    },
  },
  components: {
    DefaultCard,
    ListStudentView,
    FormBasicInfoStudent,
    FormBasicInfoStudent,
  },
};
</script>
<style scoped></style>
