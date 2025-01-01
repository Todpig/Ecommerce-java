package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição do pedido")
public class PedidoRequestDTO {

    @Schema(description = "Data do pedido", example = "2021-10-10T10:00:00")
    private String dataPedido;

    @Schema(description = "Valor total do pedido", example = "100.00")
    private String valorTotal;

    @Schema(description = "Status do pedido", example = "EM_ABERTO")
    private String statusPedido;

    @Schema(description = "Id do cliente", example = "1")
    private Long clienteId;

    // @Schema(description = "Itens do pedido")
    // private List<ItemPedidoRequestDTO> itens;
}
