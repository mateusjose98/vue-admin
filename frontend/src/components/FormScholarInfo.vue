<template>
  <form @submit.prevent="matricular">
    <div class="card-body">
      <div class="row">
        <div class="col-6">
          <div class="form-group">
            <label for="exampleFormControlSelect1">Série/Ano atual</label>
            <select
              v-model="matricula.serieId"
              class="form-control"
              id="exampleFormControlSelect1"
              @change="onChangeSerie($event)"
            >
              <option value="null">Selecione</option>
              <option v-for="s in series" :value="s.id">{{ s.nome }}</option>
            </select>
          </div>
        </div>
        <div class="col-6">
          <div class="form-group">
            <label for="exampleFormControlSelect1">Turmas</label>
            <select
              v-model="matricula.turmaId"
              class="form-control"
              id="exampleFormControlSelect1"
            >
              <option value="null">Selecione</option>
              <option v-for="s in turmas" :value="s.id">
                {{ s.descricao }}
              </option>
            </select>
          </div>
        </div>
      </div>
      <div class="form-group">
        <label for="exampleInputFile"
          >Documento de identificação do aluno(certidão, RG, etc) *</label
        >
        <div class="input-group">
          <input type="file" accept="pdf/*" @change="uploadImage" />
        </div>
      </div>
      <div class="form-group">
        <label for="exampleInputFile"
          >Histórico/declaração da escola anterior</label
        >
        <div class="input-group">
          <input type="file" accept="image/*" />
        </div>
      </div>
    </div>
    <!-- /.card-body -->

    <div class="card-footer">
      <button type="submit" class="btn btn-default">Salvar</button>
    </div>
  </form>
</template>
<script>
import TurmaService from "@/services/TurmaService";
export default {
  name: "FormScholarInfo",
  data() {
    return {
      matricula: {
        serieId: null,
        turmaId: null,
      },
      series: [],
      turmas: [],
    };
  },
  created() {
    new TurmaService().buscarSeries().then((r) => {
      this.series = r;
    });
  },
  methods: {
    onChangeSerie(e) {
      this.matricula.turmaId = null;
      new TurmaService().buscarTurmasPorSerie(e.target.value).then((r) => {
        this.turmas = r;
        console.log(r);
      });
    },
    uploadImage(e) {
      const file = e.target.files[0];
      let formData = new FormData();
      formData.append("pdf", file);
      console.log(file);
    },
    async matricular() {
      this.$store.commit("toggleLoading", true);
      const alunoService = new AlunoService();
      alunoService
        .matricular(this.matricula)
        .then((r) => {
          Toast.fire({
            icon: "success",
            title: "Matrícula realizada com sucesso",
          });
          this.$store.commit("toggleLoading", false);
        })
        .catch((e) => {})
        .finally(() => {
          this.listar();
          this.$store.commit("toggleLoading", false);
        });
    },
  },
};
</script>
<style></style>
