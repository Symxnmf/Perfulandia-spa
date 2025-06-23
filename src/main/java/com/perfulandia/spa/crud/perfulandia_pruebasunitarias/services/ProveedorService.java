package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services;

import java.util.List;
import java.util.Optional;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Proveedor;


public interface ProveedorService {

    List<Proveedor> findByAll();

    Optional<Proveedor> findById(Long id);

    Proveedor save(Proveedor unProveedor);

    Optional<Proveedor> delete (Proveedor unProveedor);

}