package br.com.catalogo.controller;

import br.com.catalogo.dto.LoginDTO;
import br.com.catalogo.dto.TokenDTO;
import br.com.catalogo.model.Usuario;
import br.com.catalogo.security.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );

            Usuario usuario = (Usuario) authentication.getPrincipal();
            String token = jwtUtil.generateToken(usuario);

            return ResponseEntity.ok(new TokenDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body(new TokenDTO("Erro de autenticação"));
        }
    }
}