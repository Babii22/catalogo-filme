package br.com.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta com os dados do filme")
public record FilmeDTO(
        @Schema(description = "ID do filme", example = "1")
        Long id,

        @Schema(description = "Título do filme", example = "Interestelar")
        String titulo,

        @Schema(description = "Descrição do filme", example = "Um grupo de exploradores viaja por um buraco de minhoca no espaço.")
        String descricao,

        @Schema(description = "Gênero do filme", example = "Ficção Científica")
        String genero
) {}