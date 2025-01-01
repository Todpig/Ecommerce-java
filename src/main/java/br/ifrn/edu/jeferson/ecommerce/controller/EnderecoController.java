package br.ifrn.edu.jeferson.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api/enderecos")
@Tag(name = "Enderecos", description = "API de gerenciamento dos enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Listar todos os enderecos")
    @GetMapping
    public ResponseEntity<List<EnderecoResponseDTO>> listar() {
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @Operation(summary = "Buscar um endereco pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> buscarPorId(Long id) {
        return ResponseEntity.ok(enderecoService.findById(id));
    }

    @Operation(summary = "Criar um novo endereco")
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> criar(@RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return ResponseEntity.ok(enderecoService.save(enderecoRequestDTO));
    }

    @Operation(summary = "Atualizar um endereco")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> atualizar(Long id, @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return ResponseEntity.ok(enderecoService.update(id, enderecoRequestDTO));
    }

    @Operation(summary = "Deletar um endereco")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
