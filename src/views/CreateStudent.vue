<template>
  <content-header title="Matricular novo aluno"></content-header>
  <content>
    <div class="row">
      <div class="col-6">
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">Informações pessoais</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form>
            <div class="card-body">
              <div class="row">
                <div class="col-9">
                  <div class="form-group">
                    <label for="exampleInputEmail1">Nome</label>
                    <input
                      type="text"
                      class="form-control"
                      id="exampleInputEmail1"
                      placeholder="Nome completo ..."
                    />
                  </div>

                  <div class="form-group">
                    <label for="exampleInputPassword1">CPF</label>
                    <input
                      type="text"
                      maxlength="11"
                      class="form-control"
                      id="exampleInputPassword1"
                      placeholder="Digite apenas os números"
                    />
                  </div>
                </div>
                <div class="col-1">
                  <div style="width: 150px">
                    <img
                      src="https://avatars.githubusercontent.com/u/48035356?v=4"
                      alt="..."
                      class="img-thumbnail"
                    />
                  </div>
                </div>
              </div>

              <div class="form-group">
                <label for="exampleInputPassword1">Data de nascimento</label>
                <input
                  type="date"
                  class="form-control"
                  id="exampleInputPassword1"
                />
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Telefone</label>
                <input
                  type="text"
                  maxlength="11"
                  class="form-control"
                  id="exampleInputPassword1"
                  placeholder="Digite apenas os números"
                />
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">Email</label>
                <input
                  type="email"
                  maxlength="150"
                  class="form-control"
                  id="exampleInputPassword1"
                  placeholder="Digite apenas os números"
                />
              </div>
              <div class="form-group">
                <label for="exampleFormControlSelect1"
                  >Estado de nascimento</label
                >
                <select class="form-control" id="exampleFormControlSelect1">
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
                  <div class="custom-file">
                    <input
                      type="file"
                      class="custom-file-input"
                      id="exampleInputFile"
                    />
                    <label class="custom-file-label" for="exampleInputFile"
                      >Escolha o arquivo</label
                    >
                  </div>
                  <div class="input-group-append">
                    <span class="input-group-text">Upload</span>
                  </div>
                </div>
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
            <!-- /.card-body -->

            <div class="card-footer">
              <button type="submit" class="btn btn-primary">Cadastrar</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </content>
</template>
<script>
import AlunoService from "../services/AlunoService";
export default {
  mounted() {
    this.buscarTodos();
  },
  methods: {
    async buscarTodos() {
      const service = new AlunoService();
      console.log(await service.getAlunos());
    },
  },
};
</script>
<style scoped></style>
