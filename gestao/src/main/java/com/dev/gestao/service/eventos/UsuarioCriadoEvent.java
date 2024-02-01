package com.dev.gestao.service.eventos;

import com.dev.gestao.domain.usuario.Usuario;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UsuarioCriadoEvent extends ApplicationEvent {
    private Usuario usuario;


    public UsuarioCriadoEvent(Object source, Usuario usuario) {
        super(source);
        this.usuario = usuario;
    }


}
