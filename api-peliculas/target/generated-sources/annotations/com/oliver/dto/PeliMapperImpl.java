package com.oliver.dto;

import com.oliver.domain.Peli;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-17T21:18:52-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
public class PeliMapperImpl implements PeliMapper {

    @Override
    public PeliculaDTO productToProductDto(Peli pelicula) {
        if ( pelicula == null ) {
            return null;
        }

        PeliculaDTO peliculaDTO = new PeliculaDTO();

        peliculaDTO.setImageUrl( pelicula.getImagePath() );
        peliculaDTO.setTitle( pelicula.getTitle() );
        peliculaDTO.setId( pelicula.getId() );
        peliculaDTO.setDescription( pelicula.getDescription() );

        return peliculaDTO;
    }

    @Override
    public Peli productDtoToProduct(PeliculaDTO peliculaDTO) {
        if ( peliculaDTO == null ) {
            return null;
        }

        String imagePath = null;
        String title = null;
        int id = 0;
        String description = null;

        imagePath = peliculaDTO.getImageUrl();
        title = peliculaDTO.getTitle();
        id = peliculaDTO.getId();
        description = peliculaDTO.getDescription();

        Peli peli = new Peli( id, imagePath, title, description );

        return peli;
    }
}
