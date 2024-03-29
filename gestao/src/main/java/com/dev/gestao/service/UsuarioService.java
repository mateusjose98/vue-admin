package com.dev.gestao.service;

import com.dev.gestao.domain.usuario.Acesso;
import com.dev.gestao.domain.usuario.Usuario;
import com.dev.gestao.domain.usuario.UsuarioDTO;
import com.dev.gestao.repository.AcessoRepository;
import com.dev.gestao.repository.UsuarioRepository;
import com.dev.gestao.service.eventos.UsuarioCriadoEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    
    final UsuarioRepository usuarioRepository;
    final PasswordEncoder passwordEncoder;
    final AcessoRepository acessoRepository;
    final ApplicationEventPublisher publisher;

    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado " + login));
    }

    public Usuario findByID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    @Transactional
    public void changePassword(String username, String oldPassword, String newPassword) {
        Usuario usuario = findByLogin(username);
        if(!passwordEncoder.matches(oldPassword, usuario.getPassword())) {
           throw new RuntimeException("Antiga senha não corresponde!");
        }
        usuario.updatePassword(passwordEncoder, newPassword);
    }

    public UsuarioDTO getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return new UsuarioDTO(this.findByLogin(authentication.getName()));
        }
        return null;
    }

    public UsuarioDTO criar(Usuario usuario, Set<Integer> acessosIds) {
        HashSet<Acesso> acessos = new HashSet<>(acessoRepository.findAllById(acessosIds));
        usuario.setAcessos(acessos);
        usuario = usuarioRepository.save(usuario);
        publisher.publishEvent(new UsuarioCriadoEvent(this, usuario));
        return new UsuarioDTO(usuario);
    }

    public Usuario criaraPartirDasCredenciais(String login, String rawPasword, String acessoDefault) {
        String encoded = passwordEncoder.encode(rawPasword);
        Usuario usuario = Usuario.fromCredentials(login, encoded);
        Set<Acesso> acesso = Set.of(acessoRepository.findByDescricao(acessoDefault));
        usuario.setAcessos(acesso);
        usuario = usuarioRepository.save(usuario);
        publisher.publishEvent(new UsuarioCriadoEvent(this, usuario));
        return usuario;
    }



}
