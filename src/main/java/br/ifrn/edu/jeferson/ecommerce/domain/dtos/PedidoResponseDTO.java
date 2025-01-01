package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de pedido")
public class PedidoResponseDTO {

    @Schema(description = "Identificador do pedido")
    private Long id;

    @Schema(description = "Data do pedido")
    private String dataPedido;

    @Schema(description = "Valor total do pedido")
    private String valorTotal;

    @Schema(description = "Status do pedido")
    private String statusPedido;

    // @Schema(description = "Cliente do pedido")
    // private ClienteResponseDTO cliente;
}
