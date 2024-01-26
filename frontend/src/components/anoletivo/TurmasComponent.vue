<template>
  <div class="card card-primary card-outline">
    <div class="card-header">
      <h2 class="card-title font-weight-bold">Turmas</h2>
      <div>
        <button @click="abrirModal" class="btn btn-info btn-sm float-right">
          <i class="fa fa-plus"></i> Adicionar
        </button>
      </div>
    </div>
    <div class="card-body">
      <div class="table-responsive p-0" style="height: 300px">
        <table class="table table-head-fixed table-hover text-nowrap">
          <thead>
            <tr>
              <th>#</th>
              <th>Descrição</th>
              <th>Ano</th>
              <th>Vagas</th>
              <th>Sala</th>
              <th>Série</th>
              <th>Valor Base</th>
              <th>Ações</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="(p, i) in turmas">
              <td>{{ i + 1 }}</td>
              <td>{{ p.descricao }}</td>
              <td>{{ p.ano }}</td>
              <td>{{ p.vagasDisponiveis }}</td>
              <td>{{ p.sala.nome }} {{ p.sala.capacidade }}</td>
              <td>{{ p.serie.nome }}</td>
              <td>R$ {{ p.valorBase }}</td>
              <td>
                <button
                  @click="desativarTurma(p.id)"
                  class="btn btn-danger btn-sm m-1"
                >
                  <i class="fa fa-ban"></i>
                </button>
                <button
                  @click="editarTurma(p.id)"
                  class="btn btn-primary btn-sm"
                >
                  <i class="fa fa-edit"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="card-footer"></div>
    <!-- <Modal labelSuccess="Salvar" titulo="Nova turma">
      <form>
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group">
              <label for="nome">Descrição</label>
              <input
                type="text"
                class="form-control"
                id="nome"
                placeholder="Nome completo ..."
              />
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="cpf">Ano</label>
              <input
                type="text"
                class="form-control"
                id="cpf"
                placeholder="Digite apenas os números"
                :value="new Date().getFullYear()"
              />
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="exampleFormControlSelect1">Sala</label>
              <select class="form-control" id="exampleFormControlSelect1">
                <option value="AC">Acre</option>
                <option value="AL">Alagoas</option>
              </select>
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="cpf"
                >Número de vagas
                <span class="small"
                  >(valor máx. é a quantidade de alunos da sala)</span
                ></label
              >
              <input
                type="text"
                class="form-control"
                id="cpf"
                placeholder="Digite apenas os números"
              />
            </div>
          </div>
          <div class="col-sm-12">
            <div class="form-group">
              <label for="exampleFormControlSelect1">Série</label>
              <select class="form-control" id="exampleFormControlSelect1">
                <option value="AC">Acre</option>
                <option value="AL">Alagoas</option>
              </select>
            </div>
          </div>
        </div>
      </form>
    </Modal> -->
  </div>
</template>
<script>
export default {
  props: {
    turmas: Array,
  },
};
</script>
<style></style>
