<template>
  <content-header
    title="Cadastro das turmas, séries e salas"
    icon="fas fa-info-circle"
  ></content-header>
  <content>
    <div class="row">
      <div class="col-sm-12 col-md-6">
        <div class="card card-primary card-outline">
          <div class="card-header">
            <h3 class="card-title font-weight-bold">Salas</h3>
          </div>
          <div class="card-body">
            <div class="table-responsive p-0" style="height: 300px">
              <table class="table table-head-fixed table-hover text-nowrap">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>nome</th>
                  </tr>
                </thead>

                <tbody>
                  <tr
                    v-for="(p, i) in [
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                    ]"
                  >
                    <td>{{ i + 1 }}</td>
                    <td>{{ p.nome }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="card-footer">Teste</div>
        </div>
      </div>
      <div class="col-sm-12 col-md-6">
        <div class="card card-primary card-outline">
          <div class="card-header">
            <h2 class="card-title font-weight-bold">Séries</h2>
          </div>
          <div class="card-body">
            <div class="table-responsive p-0" style="height: 300px">
              <table class="table table-head-fixed table-hover text-nowrap">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>nome</th>
                  </tr>
                </thead>

                <tbody>
                  <tr
                    v-for="(p, i) in [
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                      { nome: '101 A' },
                    ]"
                  >
                    <td>{{ i + 1 }}</td>
                    <td>{{ p.nome }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="card-footer">Teste</div>
        </div>
      </div>

      <div class="col-sm-12 col-md-6">
        <div class="card card-primary card-outline">
          <div class="card-header">
            <h2 class="card-title font-weight-bold">Disciplinas</h2>
          </div>
          <div class="card-body">
            <div class="table-responsive p-0" style="height: 300px">
              <table class="table table-head-fixed table-hover text-nowrap">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>nome</th>
                  </tr>
                </thead>

                <tbody>
                  <tr
                    v-for="(p, i) in [
                      { nome: 'Matemática' },
                      { nome: 'Português' },
                      { nome: 'História' },
                      { nome: 'Geografia' },
                      { nome: 'Ciências' },
                      { nome: 'Inglês' },
                      { nome: 'Educação Física' },
                    ]"
                  >
                    <td>{{ i + 1 }}</td>
                    <td>{{ p.nome }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="card-footer">Teste</div>
        </div>
      </div>
      <div class="col-sm-12 col-md-6">
        <div class="card card-primary card-outline">
          <div class="card-header">
            <h2 class="card-title font-weight-bold">Turmas</h2>
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
                  <tr
                    v-for="(p, i) in [
                      {
                        id: 1,
                        descricao: '7 ano A',
                        ano: 2022,
                        vagas: 30,
                        sala: 'Sala 101',
                        serie: '1ª Série',
                        valorBase: 1000,
                      },
                      {
                        id: 2,
                        descricao: '8 ANO C',
                        ano: 2023,
                        vagas: 25,
                        sala: 'Sala 102',
                        serie: '2ª Série',
                        valorBase: 1200,
                      },
                      {
                        id: 3,
                        descricao: 'Infantil 2',
                        ano: 2024,
                        vagas: 20,
                        sala: 'Sala 103',
                        serie: '3ª Série',
                        valorBase: 1500,
                      },
                      {
                        id: 3,
                        descricao: 'Infantil 2',
                        ano: 2024,
                        vagas: 20,
                        sala: 'Sala 103',
                        serie: '3ª Série',
                        valorBase: 1500,
                      },
                      {
                        id: 3,
                        descricao: 'Infantil 2',
                        ano: 2024,
                        vagas: 20,
                        sala: 'Sala 103',
                        serie: '3ª Série',
                        valorBase: 1500,
                      },
                      {
                        id: 3,
                        descricao: 'Infantil 2',
                        ano: 2024,
                        vagas: 20,
                        sala: 'Sala 103',
                        serie: '3ª Série',
                        valorBase: 1500,
                      },
                    ]"
                  >
                    <td>{{ i + 1 }}</td>
                    <td>{{ p.descricao }}</td>
                    <td>{{ p.ano }}</td>
                    <td>{{ p.vagas }}</td>
                    <td>{{ p.sala }}</td>
                    <td>{{ p.serie }}</td>
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
          <div class="card-footer">Teste</div>
        </div>
      </div>
    </div>
  </content>
</template>
<script>
export default {
  methods: {
    desativarTurma(id) {
      // Implemente a lógica para desativar a turma com o ID fornecido
      console.log("Desativar turma com ID:", id);
    },
    editarTurma(id) {
      // Implemente a lógica para editar a turma com o ID fornecido
      console.log("Editar turma com ID:", id);
    },
    criarTurma() {
      // Implemente a lógica para criar uma nova turma
      console.log("Criar Nova Turma");
    },
  },
};
</script>
