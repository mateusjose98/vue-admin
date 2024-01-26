<template>
  <div class="card card-primary card-outline">
    <div class="card-header">
      <h2 class="card-title font-weight-bold">Disciplinas</h2>
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
              <th>ID</th>
              <th>nome</th>
            </tr>
          </thead>

          <tbody>
            <tr v-for="(p, i) in disciplinas">
              <td>{{ i + 1 }}</td>
              <td>{{ p.nome }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="card-footer"></div>
  </div>
</template>
<script>
export default {
  props: {
    disciplinas: Array,
  },
};
</script>
<style></style>
