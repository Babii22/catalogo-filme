package br.com.catalogo.controller;

import br.com.catalogo.dto.UsuarioDTO;
import br.com.catalogo.model.Usuario;
import br.com.catalogo.repository.UsuarioRepository;
import br.com.catalogo.model.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Endpoints para cadastro de usuários")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioDTO dto) {
        if (usuarioRepository.findByLogin(dto.login()).isPresent()) {
            return ResponseEntity.badRequest().body("Usuário já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setLogin(dto.login());
        novoUsuario.setSenha(passwordEncoder.encode(dto.senha()));
        novoUsuario.setRole(dto.role() != null ? dto.role() : Role.ROLE_USER);

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso.");
    }
}