package com.dev.gestao.domain.usuario;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "acesso")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Acesso implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(nullable = false)
    private String descricao;
    @Override
    public String getAuthority() {
        return descricao;
    }

}
