import HttpClient from "./http/HttpClient";

class AuthService {
  constructor() {
    this.http = new HttpClient("auth/login");
  }

  async login(formLogin) {
    return this.http.create(`${this.http.path}`, formLogin, false);
  }
  async criar(aluno) {
    return this.http.create(this.http.path, aluno, false);
  }

  async uploadFoto(id, formData) {
    return this.http.create(
      `${this.http.path}/upsert-foto/${id}`,
      formData,
      true
    );
  }

  async getAlunos() {
    return this.http.read();
  }
}

export default AuthService;
