import HttpClient from "./http/HttpClient";


class FuncionarioService {

    constructor() {
        this.http = new HttpClient();
    }
    async inserirFuncionarios(lista) {
        return this.http.create(`api/funcionarios/batch`, lista, false);
    }
}

export default FuncionarioService;