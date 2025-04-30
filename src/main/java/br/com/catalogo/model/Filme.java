package br.com.catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "A data de lançamento é obrigatória.")
    private LocalDate dataLancamento;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    @NotNull(message = "O gênero é obrigatório.")
    private Genero genero;

    public Filme() {}

    public Filme(String titulo, String descricao, LocalDate dataLancamento, Genero genero) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataLancamento = dataLancamento;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}