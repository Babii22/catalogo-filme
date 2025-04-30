package br.com.catalogo.repository;

import br.com.catalogo.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByGenero(br.com.catalogo.model.Genero genero);

    @Query("SELECT f FROM Filme f WHERE " +
           "(:titulo IS NULL OR LOWER(f.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) AND " +
           "(:dataLancamento IS NULL OR f.dataLancamento = :dataLancamento)")
    List<Filme> filtrarPorTituloEData(@Param("titulo") String titulo,
                                       @Param("dataLancamento") LocalDate dataLancamento);
}