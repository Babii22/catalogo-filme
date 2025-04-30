package br.com.catalogo.controller;

import br.com.catalogo.dto.GeneroDTO;
import br.com.catalogo.service.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
@Tag(name = "Gêneros", description = "Endpoints relacionados aos gêneros de filmes")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Operation(summary = "Listar todos os gêneros")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Gêneros retornados com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarTodos() {
        return ResponseEntity.ok(generoService.listarTodos());
    }

    @Operation(summary = "Cadastrar um novo gênero")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Gênero cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o gênero")
    })
    @PostMapping
    public ResponseEntity<Void> adicionarGenero(@RequestBody GeneroDTO generoDTO) {
        try {
            generoService.salvar(generoDTO);
            return ResponseEntity.status(201).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @Operation(summary = "Excluir um gênero por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Gênero excluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "Não é possível excluir um gênero que possui filmes associados"),
        @ApiResponse(responseCode = "404", description = "Gênero não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGenero(@PathVariable Long id) {
        try {
            generoService.excluirPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}