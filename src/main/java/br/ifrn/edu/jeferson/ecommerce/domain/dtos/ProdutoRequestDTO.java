package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import java.util.List;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição do produto")
public class ProdutoRequestDTO {

    @Schema(description = "Nome do produto", example = "Produto 1")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Descrição do produto 1")
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Schema(description = "Preço do produto", example = "10.00")
    private String preco;

    @Schema(description = "Estoque do produto", example = "10")
    private Integer estoque;

    @Schema(description = "Categorias do produto", example = "1,2,3")
    private List<Categoria> categorias;

}
