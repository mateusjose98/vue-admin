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
                checked
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
          <button class="btn btn-warning mb-2">Simular parcelas</button>
        </div>
        <div class="col-lg-9">
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
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
                  </tr>
                  <tr>
                    <td>100</td>
                    <td>10/02/2023</td>
                    <td>Emitida</td>
                    <td>Agosto</td>
                    <td>R$ 450,50</td>
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
              >Criar acesso ao sistema para o aluno</label
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
export default {
  name: "FormFinanceiroAluno",
  data() {
    return {
      desconto: 0.0,
      aluno: {
        nome: null,
        cpf: null,
        dataNascimento: null,
        telefone: null,
        email: null,
        uf: "MA",
        criarAcesso: true,
        nomeResponsavel: null,
        cpfResponsavel: null,
      },
      foto: null,
      previewImage: null,
    };
  },

  methods: {
    validarInput() {
      this.desconto = this.desconto.replace(/[^0-9.]/g, "");
      if (this.desconto > 50) {
        this.desconto = 50.0;
      }
    },
    uploadImage(e) {
      const image = e.target.files[0];
      const reader = new FileReader();
      if (image && image.type.match("image.*")) {
        reader.readAsDataURL(image);
        this.foto = image;
        reader.onload = (e) => {
          this.previewImage = e.target.result;
        };
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
