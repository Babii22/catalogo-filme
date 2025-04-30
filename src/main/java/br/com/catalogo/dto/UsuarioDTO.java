package br.com.catalogo.dto;

import br.com.catalogo.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO para transferência de dados do usuário")
public record UsuarioDTO(

    @Schema(description = "Login do usuário", example = "usuario123")
    @NotBlank
    String login,

    @Schema(description = "Senha do usuário", example = "minhasenha123")
    @NotBlank
    String senha,

    @Schema(description = "Papel do usuário", example = "ROLE_USER")
    @NotNull
    Role role

) {}