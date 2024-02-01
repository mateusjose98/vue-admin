package com.dev.gestao.service.eventos.listener;

import com.dev.gestao.domain.enums.TipoEmailEvento;
import com.dev.gestao.service.NotificacaoService;
import com.dev.gestao.service.eventos.UsuarioCriadoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class UsuarioCriadoListener  implements ApplicationListener<UsuarioCriadoEvent> {

    final NotificacaoService notificacaoService;
    @Override
    @TransactionalEventListener(phase= TransactionPhase.AFTER_COMPLETION)
    public void onApplicationEvent(UsuarioCriadoEvent event) {
        log.info("Usuário criado: " + event.getUsuario().getLogin());
        notificacaoService.enviar(TipoEmailEvento.NOVO_USUARIO_ASSUNTO, TipoEmailEvento.NOVO_USUARIO, event.getUsuario().getLogin(), null);
    }
}
