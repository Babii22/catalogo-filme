package br.com.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta com os dados do gênero")
public record GeneroDTO(
        @Schema(description = "ID do gênero", example = "1")
        Long id,

        @Schema(description = "Nome do gênero", example = "Aventura")
        String nome
) {}