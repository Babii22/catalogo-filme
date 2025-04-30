package br.com.catalogo.controller;

import br.com.catalogo.dto.FilmeDTO;
import br.com.catalogo.dto.FilmeRequestDTO;
import br.com.catalogo.service.FilmeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@Tag(name = "Filmes", description = "Endpoints relacionados aos filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @Operation(summary = "Listar todos os filmes")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<FilmeDTO>> listarTodos() {
        try {
            return ResponseEntity.ok(filmeService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Listar filmes por gênero")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filmes por gênero retornados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/genero/{nomeGenero}")
    public ResponseEntity<List<FilmeDTO>> listarPorGenero(@PathVariable String nomeGenero) {
        try {
            return ResponseEntity.ok(filmeService.listarPorGenero(nomeGenero));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Filtrar filmes por título e/ou data de lançamento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filmes filtrados com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/filtro")
    public ResponseEntity<List<FilmeDTO>> filtrar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) LocalDate dataLancamento) {
        try {
            return ResponseEntity.ok(filmeService.filtrar(titulo, dataLancamento));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Cadastrar um novo filme")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Filme criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou gênero inexistente")
    })
    @PostMapping
    public ResponseEntity<FilmeDTO> salvar(@RequestBody @Valid FilmeRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(filmeService.salvar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Operation(summary = "Atualizar um filme existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme ou gênero não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FilmeRequestDTO dto) {
        try {
            return ResponseEntity.ok(filmeService.atualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Excluir um filme por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Filme excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            filmeService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}