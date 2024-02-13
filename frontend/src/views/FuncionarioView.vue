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

              <p>
                Insira um arquivo compatível com formato
                <span class="badge badge-success">CSV</span> para inserir os
                funcionários
              </p>
              <div></div>
              <div>
                <FileUpload
                  :maxSize="10"
                  accept="csv"
                  @file-uploaded="getUploadedData"
                  :jsonData="previewData"
                  @resetFileInput="resetData"
                />

<!--                <div v-if="fileSelected">-->
<!--                 Arquivo selecionado: {{ file.name }}.{{-->
<!--                    file.fileExtention-->
<!--                  }}-->
<!--                </div>-->
              </div>
            </div>
          </div>
          <div v-if="fileSelected" class="col-12">
           Pré-visualização
            <div class="table-responsive p-0" style="height: 300px">
              <table class="table table-head-fixed table-hover text-nowrap">
              <thead>
                <tr>
                  <th v-for="item in [...previewColumns, 'unique']">{{ item }}</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="data in previewData">
                  <td v-for="item in [...previewColumns, 'unique']">
                    {{ item === 'unique' ? (data[item] === true ? 'Sim' : 'Não') : data[item] }}
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
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
    resetData(){
      this.previewData = []
      this.previewColumns = []
      this.file = {}

    },
    getUploadedData(file) {
      this.fileSelected = true;
      this.showFileSelect = false;
      this.file = file;
      this.$papa.parse(file.blob, {
        header: true,
        skipEmptyLines: true,
        complete: (results) => {
          this.previewColumns = Object.keys(results.data[0]);
          const uniqueCPFSet = new Set();
          let nonUniqueList = results.data.filter(obj => {
            if (!uniqueCPFSet.has(obj.cpf)) {
              uniqueCPFSet.add(obj.cpf);
              return false;
            }
            return true;
          });
          results.data.forEach(obj => {
            obj.unique = !nonUniqueList.includes(obj);
          })
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
