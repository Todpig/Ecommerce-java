package br.ifrn.edu.jeferson.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;  // Importa a anotação de cache
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "API de gerenciamento de categorias dos Produtos")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Criar uma nova categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@RequestBody CategoriaRequestDTO categoriaDto) {
        return ResponseEntity.ok(categoriaService.salvar(categoriaDto));
    }

    @Operation(summary = "Listar categorias")
    @GetMapping
    @Cacheable(value = "categorias", key = "#nome + '-' + #descricao + '-' + #pageable.pageNumber")  // Aplica o cache
    public ResponseEntity<Page<CategoriaResponseDTO>> lista(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String descricao,
            Pageable pageable
    ) {
        return ResponseEntity.ok(categoriaService.lista(pageable, nome, descricao));
    }

    @Operation(summary = "Deletar uma nova categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar uma nova categoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaDto) {
        return ResponseEntity.ok(categoriaService.atualizar(id, categoriaDto));
    }

    @Operation(summary = "Adicionar um produto a uma categoria")
    @PostMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<ProdutoResponseDTO> adicionarProdutoACategoria(@PathVariable Long categoriaId, @PathVariable Long produtoId) {
        return ResponseEntity.ok(categoriaService.adicionarProdutoACategoria(categoriaId, produtoId));
    }

    @Operation(summary = "Remover um produto de uma categoria")
    @CacheEvict(value = "categorias", allEntries = true)
    @DeleteMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<ProdutoResponseDTO> removerProdutoDaCategoria(@PathVariable Long categoriaId, @PathVariable Long produtoId) {
        return ResponseEntity.ok(categoriaService.removerProdutoDaCategoria(categoriaId, produtoId));
    }
}
