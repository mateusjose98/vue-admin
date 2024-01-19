<template>
  <content-header title="Matricular novo aluno"></content-header>
  <content>
    <div class="row">
      <div class="col-3">
        <a href="mailbox.html" class="btn btn-primary btn-block mb-3"
          >Salvar estado atual</a
        >

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
                <a href="#" class="nav-link">
                  <i class="fas fa-inbox"></i> Dados básicos
                  <span class="badge bg-primary float-right">*</span>
                </a>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">
                  <i class="far fa-envelope"></i> Dados escolares
                </a>
              </li>
              <li class="nav-item">
                <a href="#" class="nav-link">
                  <i class="far fa-file-alt"></i> Financeiro
                </a>
              </li>
            </ul>
          </div>
          <!-- /.card-body -->
        </div>
      </div>
      <div class="col-sm-12 col-md-12 col-lg-9">
        <DefaultCard titulo="Cadastro">
          <FormBasicInfoStudent :aluno="{}" />
        </DefaultCard>
      </div>

      <div class="col-sm-12 col-md-12 col-lg-12">
        <DefaultCard titulo="Últimos alunos">
          <ListStudentView :lista="studentList"></ListStudentView>
        </DefaultCard>
      </div>
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
    };
  },
  created() {
    this.listar();
  },
  methods: {
    async listar() {
      const alunoService = new AlunoService();
      this.studentList = await alunoService.getAlunos();
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
