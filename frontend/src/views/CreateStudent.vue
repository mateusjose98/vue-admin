<template>
  <content-header title="Matricular novo aluno"></content-header>
  <content>
    <div class="row">
      <div class="col-sm-12 col-md-12 col-lg-6">
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">Informações pessoais</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form @submit.prevent="matricular">
            <div class="card-body">
              <div class="row">
                <div class="col-sm-12 col-md-8 col-lg-9">
                  <div class="form-group">
                    <label for="nome">Nome</label>
                    <input
                      type="text"
                      class="form-control"
                      id="nome"
                      placeholder="Nome completo ..."
                      v-model="aluno.nome"
                    />
                  </div>

                  <div class="form-group">
                    <label for="cpf">CPF</label>
                    <input
                      type="text"
                      maxlength="11"
                      class="form-control"
                      id="cpf"
                      placeholder="Digite apenas os números"
                      v-model="aluno.cpf"
                    />
                  </div>
                </div>
                <div class="col-sm-12 col-md-2 col-lg-1">
                  <div>
                    <img
                      style="height: 170px; width: auto; max-width: 170px"
                      :src="
                        previewImage
                          ? previewImage
                          : 'https://uxwing.com/wp-content/themes/uxwing/download/peoples-avatars/no-profile-picture-icon.png'
                      "
                      alt="..."
                      class="img-thumbnail"
                    />
                  </div>
                </div>
              </div>

              <div class="row">
                <div class="col-6">
                  <div class="form-group">
                    <label for="dataNascimento">Data de nascimento</label>
                    <input
                      type="date"
                      class="form-control"
                      id="dataNascimento"
                      v-model="aluno.dataNascimento"
                    />
                  </div>
                </div>
                <div class="col-6">
                  <div class="form-group">
                    <label for="telefone">Telefone</label>
                    <input
                      type="text"
                      maxlength="20"
                      class="form-control"
                      id="telefone"
                      placeholder="Digite apenas os números"
                      v-model="aluno.telefone"
                    />
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label for="email">Email</label>
                <input
                  type="email"
                  maxlength="150"
                  class="form-control"
                  id="email"
                  placeholder="Digite apenas os números"
                  v-model="aluno.email"
                />
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect1"
                  >Estado de nascimento</label
                >
                <select
                  v-model="aluno.uf"
                  class="form-control"
                  id="exampleFormControlSelect1"
                >
                  <option value="AC">Acre</option>
                  <option value="AL">Alagoas</option>
                  <option value="AP">Amapá</option>
                  <option value="AM">Amazonas</option>
                  <option value="BA">Bahia</option>
                  <option value="CE">Ceará</option>
                  <option value="DF">Distrito Federal</option>
                  <option value="ES">Espírito Santo</option>
                  <option value="GO">Goiás</option>
                  <option value="MA">Maranhão</option>
                  <option value="MT">Mato Grosso</option>
                  <option value="MS">Mato Grosso do Sul</option>
                  <option value="MG">Minas Gerais</option>
                  <option value="PA">Pará</option>
                  <option value="PB">Paraíba</option>
                  <option value="PR">Paraná</option>
                  <option value="PE">Pernambuco</option>
                  <option value="PI">Piauí</option>
                  <option value="RJ">Rio de Janeiro</option>
                  <option value="RN">Rio Grande do Norte</option>
                  <option value="RS">Rio Grande do Sul</option>
                  <option value="RO">Rondônia</option>
                  <option value="RR">Roraima</option>
                  <option value="SC">Santa Catarina</option>
                  <option value="SP">São Paulo</option>
                  <option value="SE">Sergipe</option>
                  <option value="TO">Tocantins</option>
                  <option value="EX">Estrangeiro</option>
                </select>
              </div>
              <div class="form-group">
                <label for="exampleInputFile">Foto</label>
                <div class="input-group">
                  <input type="file" accept="image/*" @change="uploadImage" />
                </div>
              </div>
              <div class="form-check">
                <input
                  type="checkbox"
                  class="form-check-input"
                  id="exampleCheck1"
                  v-model="aluno.criarAcesso"
                />
                <label class="form-check-label" for="exampleCheck1"
                  >Criar acesso ao sistema para o aluno</label
                >
              </div>
            </div>
            <!-- /.card-body -->

            <div class="card-footer">
              <button type="submit" class="btn btn-primary">Cadastrar</button>
            </div>
          </form>
        </div>
      </div>
      <div class="col-sm-12 col-md-12 col-lg-6">
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">Últimos alunos</h3>
          </div>

          <div class="row">
            <div v-for="aluno in listaAlunos" class="col-sm-12 col-lg-6">
              <blockquote>
                <p>{{ aluno.nome }}</p>
                <small>Matrícula: {{ aluno.matricula }}</small
                ><br />
                <small> Telefone: {{ aluno.telefone }} </small><br />
                <small> Email: {{ aluno.email }} </small><br />
                <small>CPF: {{ aluno.cpf }} </small>
              </blockquote>
            </div>
          </div>
        </div>
      </div>
    </div>
  </content>
</template>
<script>
import AlunoService from "../services/AlunoService";

export default {
  data() {
    return {
      aluno: {
        nome: null,
        cpf: null,
        dataNascimento: null,
        telefone: null,
        email: null,
        uf: "MA",
        criarAcesso: true,
      },
      foto: null,
      previewImage: null,
      listaAlunos: [],
    };
  },
  created() {
    this.listar();
  },
  methods: {
    async listar() {
      const alunoService = new AlunoService();
      this.listaAlunos = await alunoService.getAlunos();
    },
    uploadImage(e) {
      const image = e.target.files[0];
      const reader = new FileReader();
      console.log(image);
      if (image && image.type.match("image.*")) {
        reader.readAsDataURL(image);
        this.foto = image;
        reader.onload = (e) => {
          this.previewImage = e.target.result;
          console.log(this.previewImage);
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
<style scoped></style>
