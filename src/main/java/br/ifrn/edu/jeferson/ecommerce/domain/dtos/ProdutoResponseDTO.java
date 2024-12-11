package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import lombok.Data;

@Data
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;

    public ProdutoResponseDTO(Long id, String nome, String descricao, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
}
