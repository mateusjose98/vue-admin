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
                (vagas: {{ s.vagasDisponiveis }}, preço R$ : {{ s.valorBase }})
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
          <input
            id="identificacaoAluno"
            type="file"
            accept="pdf/*"
            @change="uploadImage"
          />
        </div>
      </div>
      <div class="form-group">
        <label for="exampleInputFile"
          >Histórico/declaração da escola anterior</label
        >
        <div class="input-group">
          <input
            id="declaracao"
            type="file"
            accept="pdf/*"
            @change="uploadImage"
          />
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
  props: {
    idAluno: null,
  },
  data() {
    return {
      matricula: {
        serieId: null,
        turmaId: null,
      },
      series: [],
      turmas: [],
      arquivos: {},
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
      const id = e.target.id;
      this.arquivos[id] = file;
    },
    async matricular() {
      const form = new FormData();
      form.append("identificacaoAluno", this.arquivos.identificacaoAluno);
      form.append("declaracao", this.arquivos.declaracao);
      form.append("alunoId", this.idAluno);
      form.append("turmaId", this.matricula.turmaId);
      this.$store.commit("toggleLoading", true);
      debugger;
      const turmaService = new TurmaService();
      turmaService
        .matricular(form)
        .then((r) => {
          Toast.fire({
            icon: "success",
            title: "Matrícula do aluno realizada com sucesso: " + r,
          });
          this.$emit("matriculaRealizada", r);
          this.$store.commit("toggleLoading", false);
        })
        .catch((e) => {})
        .finally(() => {
          this.$store.commit("toggleLoading", false);
        });
    },
  },
  emits: ["matriculaRealizada"],
};
</script>
<style></style>
