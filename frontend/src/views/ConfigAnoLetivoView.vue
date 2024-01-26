<template>
  <content-header
    title="Cadastro das turmas, séries e salas"
    icon="fas fa-info-circle"
  ></content-header>
  <content>
    <div class="row">
      <div class="col-sm-12 col-md-6">
        <SalasComponent :salas="salas"></SalasComponent>
      </div>
      <div class="col-sm-12 col-md-6">
        <SeriesComponent :series="series"></SeriesComponent>
      </div>

      <div class="col-sm-12 col-md-6">
        <DisciplinasComponent :disciplinas="disciplinas"></DisciplinasComponent>
      </div>
      <div class="col-sm-12 col-md-6">
        <TurmasComponent :turmas="turmas"></TurmasComponent>
      </div>
    </div>
  </content>
</template>
<script>
import TurmaService from "@/services/TurmaService";
import SalasComponent from "@/components/anoletivo/SalasComponent.vue";
import SeriesComponent from "@/components/anoletivo/SeriesComponent.vue";
import TurmasComponent from "@/components/anoletivo/TurmasComponent.vue";
import DisciplinasComponent from "@/components/anoletivo/DisciplinasComponent.vue";
export default {
  data() {
    return {
      turma: {},
      disciplinas: [],
      series: [],
      salas: [],
    };
  },
  async created() {
    const service = new TurmaService();
    const promiseSala = await service.buscarSalas();
    const promiseDisciplinas = await service.buscarDisciplinas();
    const promiseSeries = await service.buscarSeries();
    const promiseTurmas = await service.buscarTodasTurmas();
    Promise.all([
      promiseSala,
      promiseDisciplinas,
      promiseSeries,
      promiseTurmas,
    ]).then((values) => {
      this.salas = values[0];
      this.disciplinas = values[1];
      this.series = values[2];
      this.turmas = values[3];
    });
  },
  methods: {
    name: "ConfigAnoLetivoView",

    desativarTurma(id) {
      // Implemente a lógica para desativar a turma com o ID fornecido
      console.log("Desativar turma com ID:", id);
    },
    async editarTurma(id) {
      try {
        const turma = await buscarPorId(id);
        this.turma = turma;
      } catch (e) {
        this.turma = {};
      }
    },
    criarTurma() {
      // Implemente a lógica para criar uma nova turma
      console.log("Criar Nova Turma");
    },
  },
  components: {
    SalasComponent,
    SeriesComponent,
    TurmasComponent,
    DisciplinasComponent,
  },
};
</script>
