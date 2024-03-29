import HttpClient from "./http/HttpClient";

class AlunoService {
  constructor() {
    this.http = new HttpClient();
  }
  async criar(aluno) {
    return this.http.create("api/alunos", aluno, false);
  }

  async uploadFoto(id, formData) {
    return this.http.create(`api/alunos/upsert-foto/${id}`, formData, true);
  }

  async getAlunos() {
    return this.http.read();
  }

  async getAlunoById(alunoId) {
    try {
      const response = await fetch(`${this.baseUrl}/${alunoId}`);
      if (!response.ok) {
        const body = await response.json();
        console.log(body);
        throw new Error(`Erro ${response.status}: ${body.message}`);
      }

      const data = await response.json();
      return data;
    } catch (error) {
      this.handleErrors(error);
      throw error;
    }
  }
}

export default AlunoService;
