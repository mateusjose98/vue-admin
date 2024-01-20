<template>
  <form @submit.prevent="matricular">
    <div class="card-body">
      <div class="row">
        <div class="col-sm-12 col-md-8 col-lg-10">
          <div class="row">
            <div class="col-sm-12 col-md-6">
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
            </div>
            <div class="col-sm-12 col-md-6">
              <div class="form-group">
                <label for="cpf">CPF</label>
                <input
                  type="text"
                  class="form-control"
                  id="cpf"
                  placeholder="Digite apenas os números"
                  v-model="aluno.cpf"
                  v-mask="'###.###.###-##'"
                />
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-sm-12 col-md-6">
              <div class="form-group">
                <label for="cpfResponsavel">Nome Responsável</label>
                <input
                  type="text"
                  maxlength="300"
                  class="form-control"
                  id="nomeResponsavel"
                  placeholder="Digite o nome do responsável"
                  v-model="aluno.nomeResponsavel"
                />
              </div>
            </div>

            <div class="col-sm-12 col-md-6">
              <div class="form-group">
                <label for="cpfResponsavel">CPF Responsável</label>
                <input
                  type="text"
                  maxlength="150"
                  class="form-control"
                  id="cpfResponsavel"
                  placeholder="Digite apenas os números"
                  v-model="aluno.cpfResponsavel"
                  v-mask="'###.###.###-##'"
                />
              </div>
            </div>
          </div>
        </div>
        <div class="col-sm-12 col-md-2 col-lg-2">
          <div>
            <img
              style="height: 150px; width: auto; max-width: 150px"
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
              v-mask="'(##) #####-####'"
              class="form-control"
              id="telefone"
              placeholder="Digite apenas os números"
              v-model="aluno.telefone"
            />
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-6">
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
        </div>
        <div class="col-6">
          <div class="form-group">
            <label for="exampleFormControlSelect1">Estado de nascimento</label>
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
        </div>
      </div>
      <div class="form-group">
        <label for="exampleInputFile">Foto</label>
        <div class="input-group">
          <input type="file" accept="image/*" @change="uploadImage" />
        </div>
      </div>
    </div>
    <!-- /.card-body -->

    <div class="card-footer">
      <button type="submit" class="btn btn-default">Próximo</button>
    </div>
  </form>
</template>
<script>
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
        nomeResponsavel: null,
        cpfResponsavel: null,
      },
      foto: null,
      previewImage: null,
    };
  },
  methods: {
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
