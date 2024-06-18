package com.oliver.repository;


import com.oliver.domain.Peli;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PeliculaRepository extends MongoRepository<Peli, Integer> {

}
