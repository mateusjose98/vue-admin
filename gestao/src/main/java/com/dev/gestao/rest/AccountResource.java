package com.dev.gestao.rest;



import com.dev.gestao.service.JWTTokenService;
import com.dev.gestao.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
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

    @GetMapping("profile")
    public ResponseEntity profileDetail() {
        return ResponseEntity.ok(usuarioService.getCurrentUsername());
    }

    @PostMapping("refresh-accessToken")
    public ResponseEntity login(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        if(token == null) return ResponseEntity.badRequest().build();
        List<String> refreshToken = jwtTokenService.generateRefreshToken(token);
        return ResponseEntity.ok(new OutputLogin(refreshToken.get(0), refreshToken.get(1)));

    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody @Valid InputLogin data) {
        log.info("Tentativa de autenticar usuário {} ", data.username);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(data.username, data.password));
        List<String> jwtToken = jwtTokenService
                .generateTokens(data.username);
        log.info("Usuário {} autenticado com sucesso. ", data.username);
        return ResponseEntity.ok(new OutputLogin(jwtToken.get(0), jwtToken.get(1)));

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
