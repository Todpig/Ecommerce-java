package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoResponseDTO toResponseDTO(Produto produto);

    @Mapping(target = "id", ignore = true)
    Produto toEntity(ProdutoRequestDTO dto);

    List<ProdutoResponseDTO> toDTOList(List<Produto> produtos);
}
