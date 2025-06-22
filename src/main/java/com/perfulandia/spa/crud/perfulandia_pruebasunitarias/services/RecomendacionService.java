package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services;

import java.util.List;
import java.util.Optional;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Recomendacion;


public interface RecomendacionService {

    List<Recomendacion> findByAll();

    Optional<Recomendacion> findById(Long id);

    Recomendacion save(Recomendacion unRecomendacion);

    Optional<Recomendacion> delete(Long id);


}