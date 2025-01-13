package br.ifrn.edu.jeferson.ecommerce.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaResponseDTO toResponseDTO(Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    void updateEntityFromDTO(CategoriaRequestDTO dto, @MappingTarget Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    Categoria toEntity(CategoriaRequestDTO dto);

    default Page<CategoriaResponseDTO> toDTOList(Page<Categoria> categorias) {
        return categorias.map(this::toResponseDTO);
    }

}
