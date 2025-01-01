package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de categoria")
public class EnderecoResponseDTO {

    @Schema(description = "Identificador do endereço")
    private Long id;

    @Schema(description = "Rua do endereço")
    private String rua;

    @Schema(description = "Número do endereço")
    private String numero;

    @Schema(description = "Bairro do endereço")
    private String bairro;

    @Schema(description = "Cidade do endereço")
    private String cidade;

    @Schema(description = "Estado do endereço")
    private String estado;

    @Schema(description = "CEP do endereço")
    private String cep;
}
