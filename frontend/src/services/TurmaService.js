import HttpClient from "./http/HttpClient";

class TurmaService {
  constructor() {
    this.http = new HttpClient();
  }

  async buscarSeries() {
    return this.http.read("api/turmas/series");
  }
  async buscarTurmasPorSerie(idSerie) {
    return this.http.read(`api/turmas/series/${idSerie}`);
  }

  async criar(turma) {
    return this.http.create(this.http.path, turma, false);
  }

  async uploadFoto(id, formData) {
    return this.http.create(
      `${this.http.path}/upsert-foto/${id}`,
      formData,
      true
    );
  }

  async getturmas() {
    return this.http.read();
  }

  async getturmaById(turmaId) {
    try {
      const response = await fetch(`${this.baseUrl}/${turmaId}`);
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

export default TurmaService;
