<template>
  <form @submit.prevent="matricular">
    <div class="card-body">
      <div class="row">
        <div class="col-lg-3">
          <div class="form-group">
            <label>Dia do vencimento</label>
            <div class="custom-control custom-radio">
              <input
                class="custom-control-input"
                type="radio"
                id="customRadio1"
                name="customRadio"
                v-model="dia"
                value="5"
              />
              <label for="customRadio1" class="custom-control-label"
                >Todo dia 5</label
              >
            </div>
            <div class="custom-control custom-radio">
              <input
                class="custom-control-input"
                type="radio"
                id="customRadio2"
                name="customRadio"
                v-model="dia"
                value="10"
              />
              <label for="customRadio2" class="custom-control-label"
                >Todo dia 10</label
              >
            </div>
            <div class="custom-control custom-radio">
              <input
                class="custom-control-input"
                type="radio"
                id="customRadio3"
                name="customRadio"
                v-model="dia"
                value="15"
              />
              <label for="customRadio3" class="custom-control-label"
                >Todo dia 15</label
              >
            </div>
          </div>
          <div class="form-group">
            <i class="fa fa-percent" aria-hidden="true"></i
            ><label> Percentual de desconto</label>
            <input
              class="form-control"
              type="text"
              v-model="desconto"
              @input="validarInput"
            />
          </div>
          <button
            @click.prevent="simular"
            type="button"
            class="btn btn-warning mb-2"
          >
            Simular parcelas
          </button>
        </div>
        <div v-if="parcelas.length > 0" class="col-lg-9">
          <div class="card">
            <div class="card-header">
              <h3 class="card-title">Resumo do carnê</h3>

              <div class="card-tools"></div>
            </div>
            <!-- /.card-header -->
            <div class="card-body table-responsive p-0" style="height: 350px">
              <table class="table table-head-fixed table-hover text-nowrap">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Dt. vencimento</th>
                    <th>Status</th>
                    <th>Mês</th>
                    <th>Valor R$</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="(p, i) in parcelas">
                    <td>{{ i + 1 }}</td>
                    <td>{{ p.dataVencimento }}</td>
                    <td>{{ p.statusParcela }}</td>
                    <td>
                      {{
                        mesesEmPortugues[new Date(p.dataVencimento).getMonth()]
                      }}
                    </td>
                    <td>R$ {{ p.valor }}</td>
                  </tr>
                </tbody>
              </table>
            </div>

            <!-- /.card-body -->
          </div>
          <div class="form-check">
            <input
              type="checkbox"
              class="form-check-input"
              id="exampleCheck1"
            />
            <label class="form-check-label" for="exampleCheck1"
              >Criar acesso ao sistema para o aluno
              <span class="small"
                >(Obs.: Um email será enviado para o aluno com as instruções
                para login)</span
              ></label
            >
          </div>
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
  name: "FormFinanceiroAluno",
  props: {
    idMatricula: { type: Number, default: 100 },
  },
  data() {
    return {
      mesesEmPortugues: [
        "Janeiro",
        "Fevereiro",
        "Março",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novembro",
        "Dezembro",
      ],
      desconto: 0.0,
      dia: 5,
      parcelas: [],
    };
  },

  methods: {
    simular() {
      new TurmaService()
        .simular(this.dia, this.desconto, this.idMatricula)
        .then((r) => {
          this.parcelas = r;
        });
    },
    validarInput() {
      this.desconto = this.desconto.replace(/[^0-9.]/g, "");
      if (this.desconto > 50) {
        this.desconto = 50.0;
      }
    },
    async matricular() {
      this.$store.commit("toggleLoading", true);
      const alunoService = new AlunoService();
      alunoService
        .criar(this.aluno)
        .then((r) => {
          if (this.foto) {
            let data = new FormData();
            data.append("file", this.foto);
            alunoService.uploadFoto(r, data);
          }
          Toast.fire({
            icon: "success",
            title: "Aluno cadastrado com sucesso: " + r,
          });
          this.$store.commit("toggleLoading", false);
        })
        .catch((e) => {
          this.$store.commit("toggleLoading", false);
          console.log(e);
        })
        .finally(() => {
          this.listar();
          this.$store.commit("toggleLoading", false);
        });
    },
  },
};
</script>
<style></style>
