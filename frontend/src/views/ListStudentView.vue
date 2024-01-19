<template scoped>
  <div class="row">
    <div v-for="aluno in lista" class="col-sm-12 col-lg-6">
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
</template>
<script>
export default {
  name: "ListStudentView",
  props: {
    lista: Array,
  },
};
</script>
<style scoped></style>
