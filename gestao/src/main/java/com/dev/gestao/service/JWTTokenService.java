package com.dev.gestao.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dev.gestao.domain.usuario.Acesso;
import com.dev.gestao.domain.usuario.Usuario;
import com.dev.gestao.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JWTTokenService {

    @Value(
            "${secretKey}"
    )
    private String secretKey;

    @Value(
            "${spring.application.name}"
    )
    private String appName;
    @Value("${jwt.cookieExpiry}")
    private int cookieExpiry;

    public List<String> generateTokens(Usuario usuario) {

        String accessToken = generateToken(usuario.getUsername(), DateUtils.instantAfter(cookieExpiry), usuario.getAcessos() );
        String refreshToken = generateToken(usuario.getUsername(), DateUtils.instantAfter(cookieExpiry + 1 ), null);

        return List.of(accessToken, refreshToken);
    }

    public String generateToken(String username, Instant expiresAt, Set<Acesso> acessos) {
        log.info("Gerando token para usuário {}", username);

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            var token = JWT.create()
                    .withIssuer(appName)
                    .withSubject(username)
                    .withClaim("chave", "dados extras(perfil, roels, etc)")
                    .withExpiresAt(
                            expiresAt
                    );
            if(acessos != null) {
                List<Acesso> mainList = new ArrayList<>();
                mainList.addAll(acessos);
                token.withClaim("acessos", mainList.stream().map(Acesso::getDescricao).collect(Collectors.toList()));
            }
            return token.sign(algorithm);
        } catch (JWTCreationException exception){
            log.error("Erro ao gerar token para usuário {}", username);
            throw new RuntimeException("Erro ao gerar token para usuário {}");
        }

    }


    public boolean isTokenValid(String token) {

        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm =  Algorithm.HMAC256(secretKey);
            JWTVerifier verifier =JWT.require(Algorithm.HMAC256(secretKey))
                    .withIssuer(appName)
                    .build();

            decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            log.error("{}", exception.getMessage());
        }
        return false;
    }


    public List<String> generateRefreshToken(Usuario usuario) {
        return this.generateTokens(usuario);
    }

    public String getSubject(String tokenJWT) {
        try {
            return JWT.require(Algorithm.HMAC256(secretKey))
                    .withIssuer(appName)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
