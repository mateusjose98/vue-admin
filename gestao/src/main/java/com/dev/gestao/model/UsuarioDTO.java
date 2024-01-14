package com.dev.gestao.model;

import com.dev.gestao.domain.Usuario;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;
    @NotEmpty
    private String login;
    private String senha;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
    }

    public UsuarioDTO from(String login, String senha) {
        this.login = login;
        this.senha = senha;
        return this;
    }

}
