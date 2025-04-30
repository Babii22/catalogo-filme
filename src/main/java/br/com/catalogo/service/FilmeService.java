package br.com.catalogo.service;

import br.com.catalogo.dto.FilmeDTO;
import br.com.catalogo.dto.FilmeRequestDTO;
import br.com.catalogo.model.Filme;
import br.com.catalogo.model.Genero;
import br.com.catalogo.repository.FilmeRepository;
import br.com.catalogo.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private GeneroRepository generoRepository;

    public List<FilmeDTO> listarTodos() {
        return filmeRepository.findAll()
                .stream()
                .map(filme -> new FilmeDTO(filme.getId(), filme.getTitulo(), filme.getDescricao(), filme.getGenero().getNome()))
                .collect(Collectors.toList());
    }

    public List<FilmeDTO> listarPorGenero(String nomeGenero) {
        Genero genero = generoRepository.findByNome(nomeGenero)
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));
        return filmeRepository.findByGenero(genero)
                .stream()
                .map(filme -> new FilmeDTO(filme.getId(), filme.getTitulo(), filme.getDescricao(), genero.getNome()))
                .collect(Collectors.toList());
    }

    public FilmeDTO salvar(FilmeRequestDTO dto) {
        Genero genero = generoRepository.findById(dto.getGeneroId())
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));

        Filme filme = new Filme();
        filme.setTitulo(dto.getTitulo());
        filme.setDescricao(dto.getDescricao());
        filme.setDataLancamento(dto.getDataLancamento());
        filme.setGenero(genero);

        Filme salvo = filmeRepository.save(filme);
        return new FilmeDTO(salvo.getId(), salvo.getTitulo(), salvo.getDescricao(), salvo.getGenero().getNome());
    }

    public List<FilmeDTO> filtrar(String titulo, LocalDate dataLancamento) {
        return filmeRepository.filtrarPorTituloEData(titulo, dataLancamento)
                .stream()
                .map(filme -> new FilmeDTO(filme.getId(), filme.getTitulo(), filme.getDescricao(), filme.getGenero().getNome()))
                .collect(Collectors.toList());
    }

    public FilmeDTO atualizar(Long id, FilmeRequestDTO dto) {
        Filme filme = filmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        Genero genero = generoRepository.findById(dto.getGeneroId())
                .orElseThrow(() -> new RuntimeException("Gênero não encontrado"));

        filme.setTitulo(dto.getTitulo());
        filme.setDescricao(dto.getDescricao());
        filme.setDataLancamento(dto.getDataLancamento());
        filme.setGenero(genero);

        Filme atualizado = filmeRepository.save(filme);
        return new FilmeDTO(atualizado.getId(), atualizado.getTitulo(), atualizado.getDescricao(), atualizado.getGenero().getNome());
    }

    public void excluir(Long id) {
        if (!filmeRepository.existsById(id)) {
            throw new RuntimeException("Filme não encontrado");
        }
        filmeRepository.deleteById(id);
    }
}