package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PedidoMapper {

    PedidoResponseDTO toResponseDTO(Pedido pedido);

    Pedido toEntity(PedidoRequestDTO dto);

    List<PedidoResponseDTO> toDTOList(List<Pedido> pedidos);
}
