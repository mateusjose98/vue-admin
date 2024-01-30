import HttpClient from "./http/HttpClient";

class TurmaService {
  constructor() {
    this.http = new HttpClient();
  }

  async buscarTodasTurmas() {
    return this.http.read(`api/turmas`);
  }
  async buscarSeries() {
    return this.http.read("api/series");
  }
  async criarSerie(serie) {
    return this.http.create(`api/series`, serie, false);
  }
  async buscarSalas() {
    return this.http.read("api/salas");
  }

  async criarSala(sala) {
    return this.http.create(`api/salas`, sala, false);
  }

  async buscarDisciplinas() {
    return this.http.read("api/disciplinas");
  }

  async criarDisciplina(disciplina) {
    return this.http.create(`api/disciplinas`, disciplina, false);
  }

  async buscarTurmasPorSerie(idSerie) {
    return this.http.read(`api/turmas/series/${idSerie}`);
  }

  async criar(turma) {
    return this.http.create(`api/turmas`, turma, false);
  }
  async matricular(formData) {
    return this.http.create(`api/matriculas`, formData, true);
  }

  async simular(dia, desconto, idMatricula) {
    return this.http.read(
      `api/carnes/parcelas/simulacao?dia=${dia}&desconto=${desconto}&idMatricula=${idMatricula}`
    );
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
