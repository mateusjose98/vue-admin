export default class HttpClient {
  constructor() {
    this.baseURL = process.env.VUE_APP_API_URL;
  }

  async request(path, method, data = null, isMultipart) {
    try {
      const url = `${this.baseURL}${path}`;
      const options = {
        method,
        headers: !isMultipart
          ? {
              "Content-Type": "application/json",
            }
          : {},
        body: data ? (isMultipart ? data : JSON.stringify(data)) : null,
      };

      const response = await fetch(url, options);

      if (response.status >= 200 && response.status <= 299) {
        return await response.json();
      } else {
        const body = await response.json();
        if (body.message) {
          throw new Error(`[${response.status}]: ${body.message}`);
        } else {
          for (let element of body.fieldErrors) {
            throw new Error(`[${response.status}]: ${element.description}`);
          }
        }
      }
    } catch (error) {
      this.handleErrors(error);
      throw error;
    }
  }

  async create(path, data, isMultipart) {
    return this.request(path, "POST", data, isMultipart);
  }

  async read(path) {
    return this.request(path, "GET");
  }

  async update(path, data) {
    return this.request(path, "PUT", data);
  }

  async del() {
    return this.request(this.path, "DELETE");
  }

  handleErrors(error) {
    console.log(error);
    if (error instanceof Error) {
      Toast.fire({
        icon: "error",
        title: "Erro",
        text: error.message,
      });
    } else {
      Toast.fire({
        icon: "error",
        title: "Erro Desconhecido",
        text: "Ocorreu um erro desconhecido. Tente novamente mais tarde.",
      });
    }
  }
}
