package com.dev.gestao.domain.usuario;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;
    @NotEmpty
    private String login;
    private String senha;
    private Set<Integer> acessos = new HashSet<>();

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
