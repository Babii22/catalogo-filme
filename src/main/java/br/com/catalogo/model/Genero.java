package br.com.catalogo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Genero() {}

    public Genero(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genero genero)) return false;
        return Objects.equals(id, genero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}