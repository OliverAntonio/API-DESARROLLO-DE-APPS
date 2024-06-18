package com.oliver.service;

import com.oliver.domain.Peli;
import com.oliver.dto.PeliculaDTO;
import com.oliver.dto.PeliMapper;
import com.oliver.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public  class Service {

    @Autowired
    PeliculaRepository repository;

    public List<PeliculaDTO> getAllProducts() {

        List<Peli> peliculas = repository.findAll();

        List<PeliculaDTO> peliculaDTOS = peliculas.stream().map(
                pelicula -> PeliMapper.mapper.productToProductDto(pelicula)).collect(Collectors.toList());

        return peliculaDTOS;
    }


    public void createProduct(PeliculaDTO peliculaDTO) {

        Peli pelicula = PeliMapper.mapper.productDtoToProduct(peliculaDTO);
        pelicula.setDistributor("ACME");
        Date dateNow = new Date();
        pelicula.setReleaseDate(dateNow);
        repository.save(pelicula);


    }

    public PeliculaDTO updateProduct(int productId, PeliculaDTO peliculaDTO) {
        Peli pelicula = repository.findById(productId).orElse(null);

        if (pelicula !=null){
            pelicula.setTitle(peliculaDTO.getTitle());
            pelicula.setDescription(peliculaDTO.getDescription());
            pelicula = repository.save(pelicula);
        }

        PeliculaDTO response =   PeliMapper.mapper.productToProductDto(pelicula);

        return response;
    }

    public void deleteProduct(int productId) {
        Peli pelicula = repository.findById(productId).orElse(null);

        if (pelicula !=null){
            repository.delete(pelicula);
        }
    }







}
