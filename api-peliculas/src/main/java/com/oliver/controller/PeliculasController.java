package com.oliver.controller;


import com.oliver.dto.PeliculaDTO;
import com.oliver.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PeliculasController {

    @Autowired
    private Service service;
    @GetMapping(value="/products",produces = "application/json")
    public ResponseEntity getPeliculas() {

        List<PeliculaDTO> response = service.getAllProducts();

        return new ResponseEntity(response,HttpStatus.OK);

    }

    @PostMapping(value = "/products")
    public ResponseEntity guardarPelicula(@RequestBody PeliculaDTO peliculaDTO){

        service.createProduct(peliculaDTO);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity modificarPelicula(@PathVariable int productId, @RequestBody PeliculaDTO peliculaDTO){

        PeliculaDTO response =  service.updateProduct(productId, peliculaDTO);
        return new ResponseEntity(response,HttpStatus.OK);

    }


    @DeleteMapping(value = "/{productId}")
    public ResponseEntity eliminarPelicula(@PathVariable int productId){

        service.deleteProduct(productId);
        return new ResponseEntity(HttpStatus.OK);

    }
}
