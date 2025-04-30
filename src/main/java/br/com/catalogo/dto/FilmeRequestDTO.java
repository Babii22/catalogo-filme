package br.com.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "DTO para requisição de criação de filme")
public class FilmeRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Schema(description = "Título do filme", example = "Interestelar")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Schema(description = "Descrição do filme", example = "Exploração espacial e buracos de minhoca.")
    private String descricao;

    @NotNull(message = "A data de lançamento é obrigatória")
    @Schema(description = "Data de lançamento do filme", example = "2014-11-07")
    private LocalDate dataLancamento;

    @NotNull(message = "O ID do gênero é obrigatório")
    @Schema(description = "ID do gênero", example = "1")
    private Long generoId;

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(Long generoId) {
        this.generoId = generoId;
    }
}