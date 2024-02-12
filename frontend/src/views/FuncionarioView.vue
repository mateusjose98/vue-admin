<template>
  <content-header
    title="Funcionários"
    icon="fas fa-info-circle"
  ></content-header>
  <content>
    <div class="row">
      <div class="col-12">
        <div class="card card-primary card-outline">
          <div class="card-header">
            <h3 class="card-title font-weight-bold">Área de upload de CSV</h3>
            <div></div>
          </div>
          <div class="card-body">
            <div class="d-flex align-items-center flex-column">
              <h1>Funcionários</h1>
              <p>
                Insira um arquivo compatível com formato
                <span class="badge badge-success">EXCEL</span> para inserir os
                funcionários
              </p>
              <div></div>
              <div>
                <FileUpload
                  :maxSize="10"
                  accept="csv"
                  @file-uploaded="getUploadedData"
                />

                <div v-if="fileSelected">
                  Successfully Selected file: {{ file.name }}.{{
                    file.fileExtention
                  }}
                </div>
              </div>
            </div>
          </div>
          <div class="col-12">
            Confira aqui sua planilha, caso não seja esta, atualize:
            <table class="table">
              <thead>
                <tr>
                  <th v-for="item in previewColumns">{{ item }}</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="data in previewData">
                  <!-- <td >{{ data }}</td> -->
                  <td>{{ data.nome }}</td>
                  <td>{{ data.email }}</td>
                  <td>{{ data.gender }}</td>
                  <td>{{ data.tipo }}</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </content>
</template>
<script>
import FileUpload from "@/components/FileUpload.vue";

export default {
  name: "FuncionarioView",
  components: { FileUpload },
  data() {
    return {
      file: {},
      fileSelected: false,
      showFileSelect: false,
      previewColumns: [],
      previewData: [],
    };
  },
  methods: {
    getUploadedData(file) {
      this.fileSelected = true;
      this.showFileSelect = false;
      this.file = file;
      this.$papa.parse(file.blob, {
        header: true,
        skipEmptyLines: true,
        complete: (results) => {
          // get keys of object
          this.previewColumns = Object.keys(results.data[0]);
          this.previewData = results.data;
        },
      });
    },
  },
};
</script>
<style scoped>
.file-upload {
  width: 100%;
  display: flex;
  align-items: flex-start;
  justify-content: center;
}
.file-upload .file-upload__area {
  width: 600px;
  min-height: 200px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px dashed #ccc;
  margin-top: 40px;
}
</style>
