package com.oliver.dto;

import com.oliver.domain.Peli;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PeliMapper {

    PeliMapper mapper = Mappers.getMapper(PeliMapper.class);

    @Mapping(source = "imagePath" , target = "imageUrl")
    PeliculaDTO productToProductDto(Peli pelicula);

    @Mapping(source = "imageUrl" , target = "imagePath")
    Peli productDtoToProduct(PeliculaDTO peliculaDTO);

}
