package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

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
@Schema(description = "DTO para requisição do endereco")
public class EnderecoRequestDTO {

    @NotBlank
    @Schema(description = "Rua do endereco", example = "Rua do endereco")
    private String rua;
    @NotBlank
    @Schema(description = "Numero do endereco", example = "Numero do endereco")
    private String numero;
    @NotBlank
    @Schema(description = "Bairro do endereco", example = "Bairro do endereco")
    private String bairro;
    @NotBlank
    @Schema(description = "Cidade do endereco", example = "Cidade do endereco")
    private String cidade;
    @NotBlank
    @Schema(description = "Estado do endereco", example = "Estado do endereco")
    private String estado;
    @NotBlank
    @Schema(description = "CEP do endereco", example = "CEP do endereco")
    private String cep;
    @NotBlank
    @Schema(description = "ID do cliente", example = "1")
    private Long clienteId;
}
