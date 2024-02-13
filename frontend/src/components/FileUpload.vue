<template>
  <div class="file-upload">
    <div class="file-upload__area">
      <div v-if="!file.isUploaded">
        <input type="file" name="" id="" @change="handleFileChange($event)" />

        <div v-if="errors.length > 0">
          <div
            class="file-upload__error"
            v-for="(error, index) in errors"
            :key="index"
          >
            <span>{{ error }}</span>
          </div>
        </div>
      </div>
      <div v-if="file.isUploaded" class="upload-preview">
        <img :src="file.url" v-if="file.isImage" class="file-image" alt="" />

        <span class="file-name">
          Nome do arquivo escolhido: {{ file.name }}.{{ file.fileExtention }}
        </span>
        <div class="mt-1">
          <button class="btn btn-success m-1" @click="enviarArquivo">
            Quero este <i class="fa fa-thumbs-up" aria-hidden="true"></i>
          </button>
          <button class="btn btn-danger m-1" @click="resetFileInput">
            Quero outro <i class="fa fa-thumbs-down" aria-hidden="true"></i>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

import FuncionarioService from "@/services/FuncionarioService"
export default {
  name: "FileUpload",
  data() {
    return {
      errors: [],
      isLoading: false,
      uploadReady: true,
      file: {
        name: "",
        size: 0,
        type: "",
        fileExtention: "",
        url: "",
        isImage: false,
        isUploaded: false,
        blob: null,
      },
    };
  },
  props: {
    jsonData: {
      type: Array
    },
    maxSize: {
      type: Number,
      default: 5,
      required: true,
    },
    accept: {
      type: String,
      default: ".csv",
    },
  },
  methods: {
     removerDuplicatasPorCPF(array) {
      const uniqueCPFSet = new Set();
      return array.filter(obj => {
        if (!uniqueCPFSet.has(obj.cpf)) {
          uniqueCPFSet.add(obj.cpf);
          return true;
        }
        return false;
      });
    },
    enviarArquivo() {

      let service = new FuncionarioService();
      if(this.jsonData) {

        const copy = this.removerDuplicatasPorCPF(this.jsonData);

        if (copy.length < this.jsonData.length) {
          Toast.fire({
            icon: "danger",
            title: `No arquivo existem cpfs repeditidos!
                    Ajuste o arquivo e tente novamente!`,
          });
          this.resetFileInput();
          return;
        }
        const chunkSize = 20;
        const chunks = [];
        for (let i = 0; i <  copy.length; i += chunkSize) {
          const chunk = copy.slice(i, i + chunkSize);
          chunks.push(chunk);
        }


        for (let i = 0; i < chunks.length; i++) {
          console.log(chunks[i])
          service.inserirFuncionarios(chunks[i]).then((r) => {
            console.log(r)
            Toast.fire({
              icon: r === true ? "success" : "danger",
              title: `Parte : ${i + 1} enviada`,
            });
          }).catch(e => {
            console.error(e)
          });
        }
      }
    },

    handleFileChange(e) {
      this.errors = [];
      if (e.target.files && e.target.files[0]) {
        if (this.isFileValid(e.target.files[0])) {
          const file = e.target.files[0],
            fileSize = Math.round((file.size / 1024 / 1024) * 100) / 100,
            fileExtention = file.name.split(".").pop(),
            fileName = file.name.split(".").shift(),
            isImage = ["jpg", "jpeg", "png", "gif"].includes(fileExtention);

          console.log(file, fileSize, fileExtention, fileName, isImage);

          this.file = {
            name: fileName,
            size: fileSize,
            type: file.type,
            fileExtention: fileExtention,
            isImage: isImage,
            isUploaded: true,
            blob: file,
          };
        } else {
          console.log("Arquivo inválido!");
        }
      }

      if(this.file) {
        this.$emit("file-uploaded", this.file);
      }
    },
    isFileSizeValid(fileSize) {
      if (fileSize <= this.maxSize) {
        console.log("File size is valid");
      } else {
        this.errors.push(
          `Tamanho do arquivo deve ser menor que ${this.maxSize} MB`
        );
      }
    },
    isFileTypeValid(fileExtention) {
      if (this.accept.split(",").includes(fileExtention)) {
        console.log("File type is valid");
      } else {
        this.errors.push(`Tipo do arquivo deve ser ${this.accept}`);
      }
    },
    isFileValid(file) {
      this.isFileSizeValid(Math.round((file.size / 1024 / 1024) * 100) / 100);
      this.isFileTypeValid(file.name.split(".").pop());
      if (this.errors.length === 0) {
        return true;
      } else {
        return false;
      }
    },
    resetFileInput() {
      this.uploadReady = false;
      this.$nextTick(() => {
        this.uploadReady = true;
        this.file = {
          name: "",
          size: 0,
          type: "",
          data: "",
          fileExtention: "",
          url: "",
          isImage: false,
          isUploaded: false,
        };
      });
      this.$emit('resetFileInput');
    },
  },
  emits: ["file-uploaded", 'resetFileInput'],
};
</script>

<style>
.file-upload .file-upload__error {
  margin-top: 10px;
  color: #f00;
  font-size: 12px;
}

.file-upload .upload-preview .file-image {
  width: 100%;
  height: 300px;
  object-fit: contain;
}
.file-upload .upload-preview .file-extension {
  height: 100px;
  width: 100px;
  border-radius: 8px;
  background: #ccc;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0.5em auto;
  font-size: 1.2em;
  padding: 1em;
  text-transform: uppercase;
  font-weight: 500;
}
.file-upload .upload-preview .file-name {
  font-size: 1.2em;
  font-weight: 500;
  color: #000;
  opacity: 0.5;
}
</style>
