package com.dev.gestao.api;


import com.dev.gestao.domain.usuario.Usuario;
import com.dev.gestao.domain.usuario.UsuarioDTO;
import com.dev.gestao.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {


    final UsuarioService usuarioService;
    final PasswordEncoder encoder;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.criar(this.toEntity(usuarioDTO), usuarioDTO.getAcessos());
        return new ResponseEntity<>(usuarioDTO, HttpStatus.CREATED);
    }

    public Usuario toEntity(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(encoder.encode(dto.getSenha()));
        return usuario;
    }
}
