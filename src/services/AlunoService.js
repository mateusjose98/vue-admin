import Swal from "sweetalert2";

class AlunoService {
  constructor() {
    this.baseUrl = process.env.VUE_APP_API_URL + "api/alunos";
  }

  async getAlunos() {
    try {
      const response = await fetch(`${this.baseUrl}`);
      if (!response.ok) {
        throw new Error(`Erro ${response.status}: ${response.statusText}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      this.handleErrors(error);
      throw error;
    }
  }

  async getAlunoById(alunoId) {
    try {
      const response = await fetch(`${this.baseUrl}/${alunoId}`);
      if (!response.ok) {
        throw new Error(`Erro ${response.status}: ${response.statusText}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      this.handleErrors(error);
      throw error;
    }
  }

  handleErrors(error) {
    if (error instanceof Error) {
      Swal.fire({
        icon: "error",
        title: "Erro",
        text: error.message,
      });
    } else {
      Swal.fire({
        icon: "error",
        title: "Erro Desconhecido",
        text: "Ocorreu um erro desconhecido. Tente novamente mais tarde.",
      });
    }
  }
}

export default AlunoService;
