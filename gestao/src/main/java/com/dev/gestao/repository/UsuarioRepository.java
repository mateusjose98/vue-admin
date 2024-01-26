package com.dev.gestao.repository;


import com.dev.gestao.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

    @EntityGraph(attributePaths = "acessos")
    Optional<Usuario> findByLogin(String login);

    @Transactional
    @Modifying
    @Query(
            value = """
            insert into usuario_acesso(usuario_id, acesso_id) values(?1, (select id from acesso where descricao = 'ROLE_USER'))
            """, nativeQuery = true)
    void adicionaAcessoAUser(Long id);
}