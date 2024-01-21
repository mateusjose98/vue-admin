<template>
  <content-header title="Matricular novo aluno"></content-header>
  <content>
    <div class="row">
      <div class="col-sm-12 col-md-10">
        <div class="card">
          <ul class="nav nav-pills">
            <li class="nav-item">
              <a
                href="#"
                @click="step = 1"
                class="nav-link"
                :class="{ active: step === 1 }"
              >
                <i class="far fa-address-card"></i> Básico
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
                <i class="fas fa-user-graduate"></i> Matrícula
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

          <!-- /.card-body -->
        </div>
      </div>
      <div class="col-sm-12 col-md-2 mb-2">
        <a href="mailbox.html" class="btn btn-success btn-block"
          ><span class="mr-1">Concluir </span
          ><i class="fa fa-check" aria-hidden="true"></i
        ></a>
      </div>

      <!-- <div class="col-sm-12 col-md-12 col-lg-12">
        <DefaultCard titulo="Últimos alunos"> <ListStudentView /></DefaultCard>
      </div> -->
    </div>
    <div class="row">
      <div v-show="step === 1" class="col-sm-12 col-md-10">
        <DefaultCard titulo="Cadastro">
          <FormBasicInfoStudent
            @alunoCriado="(id) => (idAluno = id)"
            :aluno="{}"
          />
        </DefaultCard>
      </div>
      <div v-show="step === 2" class="col-sm-12 col-md-10">
        <DefaultCard titulo="Dados Escolares e documentação">
          <FormScholarInfo
            @matriculaRealizada="(id) => (idMatricula = id)"
            :idAluno="idAluno"
          />
        </DefaultCard>
      </div>
      <div v-show="step === 3" class="col-sm-12 col-md-10">
        <DefaultCard titulo="Financeiro, descontos">
          <FormFinanceiroAluno :idMatricula="idMatricula" />
        </DefaultCard>
      </div>
    </div>
  </content>
</template>
<script>
import DefaultCard from "@/components/DefaultCard.vue";
import FormFinanceiroAluno from "@/components/FormFinanceiroAluno.vue";
import FormScholarInfo from "@/components/FormScholarInfo.vue";
import AlunoService from "../services/AlunoService";
import ListStudentView from "./ListStudentView.vue";
import FormBasicInfoStudent from "@/components/FormBasicInfoStudent.vue";

export default {
  data() {
    return {
      studentList: [],
      step: 1,
      idAluno: null,
    };
  },
  created() {
    // this.listar();
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
    FormScholarInfo,
    FormFinanceiroAluno,
  },
};
</script>
<style scoped></style>
