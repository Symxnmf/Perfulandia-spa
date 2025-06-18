package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services;


import java.util.List;
import java.util.Optional;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Inventario;


public interface InventarioService {

    List<Inventario> findByAll();

    Optional<Inventario> findById(Long id);

    Inventario save(Inventario unInventario);

    void deleteById(Long id); 

}
