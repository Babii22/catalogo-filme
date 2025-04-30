package br.com.catalogo.service;

import br.com.catalogo.dto.GeneroDTO;
import br.com.catalogo.model.Filme;
import br.com.catalogo.model.Genero;
import br.com.catalogo.repository.FilmeRepository;
import br.com.catalogo.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    public List<GeneroDTO> listarTodos() {
        return generoRepository.findAll()
                .stream()
                .map(genero -> new GeneroDTO(genero.getId(), genero.getNome()))
                .collect(Collectors.toList());
    }

    public void salvar(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setNome(generoDTO.nome());
        generoRepository.save(genero);
    }

    public Genero buscarPorId(Long id) {
        Optional<Genero> genero = generoRepository.findById(id);
        return genero.orElse(null);
    }

    public void excluirPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));

        List<Filme> filmesComGenero = filmeRepository.findByGenero(genero);
        if (!filmesComGenero.isEmpty()) {
            throw new IllegalStateException("Não é possível excluir um gênero com filmes associados");
        }

        generoRepository.deleteById(id);
    }
}