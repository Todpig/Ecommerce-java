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

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API de gerenciamento dos pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Listar todos os pedidos")
    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @Operation(summary = "Buscar um pedido pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @Operation(summary = "Criar um novo pedido")
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        return ResponseEntity.ok(pedidoService.save(pedidoRequestDTO));
    }

    @Operation(summary = "Atualizar um pedido")
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizar(Long id, @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        return ResponseEntity.ok(pedidoService.update(id, pedidoRequestDTO));
    }

    @Operation(summary = "Deletar um pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
