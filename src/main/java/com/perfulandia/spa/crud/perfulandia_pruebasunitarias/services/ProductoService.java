package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services;

import java.util.List;
import java.util.Optional;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Producto;


public interface ProductoService {

    List<Producto> findByAll();

    Optional<Producto> findById(Long id);

    Producto save(Producto unProducto);

    Optional<Producto> delete(Long id);


}