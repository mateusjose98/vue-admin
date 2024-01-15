package com.dev.gestao.rest;



import com.dev.gestao.domain.usuario.Usuario;
import com.dev.gestao.service.JWTTokenService;
import com.dev.gestao.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Log4j2
public class AccountResource {

    final JWTTokenService jwtTokenService;
    final AuthenticationManager authenticationManager;
    final UsuarioService usuarioService;
    @Value("${jwt.cookieExpiry}")
    private int cookieExpiry;

    @GetMapping("profile")
    public ResponseEntity profileDetail() {
        return ResponseEntity.ok(usuarioService.getCurrentUsername());
    }

    @PostMapping("refresh-accessToken")
    public ResponseEntity login(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        if(token == null) return ResponseEntity.badRequest().build();
        Usuario usuario = usuarioService.findByLogin(jwtTokenService.getSubject(token));
        List<String> refreshToken = jwtTokenService.generateRefreshToken(usuario);
        return ResponseEntity.ok(new OutputLogin(refreshToken.get(0), refreshToken.get(1)));

    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid InputLogin data, HttpServletResponse response) {
        log.info("Tentativa de autenticar usuário {} ", data.username);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.username, data.password));
        if(authentication.isAuthenticated()) {
            Usuario usuario = usuarioService.findByLogin(data.username);
            List<String> jwtToken = jwtTokenService.generateTokens(usuario);
            log.info("Usuário {} autenticado com sucesso. ", data.username);
            ResponseCookie cookie = ResponseCookie.from("accessToken", jwtToken.get(0))
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(cookieExpiry)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            return ResponseEntity.ok(new OutputLogin(jwtToken.get(0), jwtToken.get(1)));
        }

        return ResponseEntity.status(401).build();


    }

    @PutMapping("change-password")
    @PreAuthorize("hasRole('SUPER_ADMIN') or principal.username == #data.username")
    public ResponseEntity changePassword(@RequestBody InputChangePassword data) {
        log.info("Tentativa de modificar senha do usuário {} ", data.username);
        usuarioService.changePassword(data.username, data.oldPassword, data.newPassword);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    record InputChangePassword(String oldPassword, String newPassword, String username){}

    record OutputLogin(String accessToken, String refreshToken) {}

    record InputLogin(@CPF(message = "cpf inválido") String username, @NotBlank String password){};
}
