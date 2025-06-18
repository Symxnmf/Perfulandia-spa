package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Inventario;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.repository.InventarioRepository;


@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> findByAll() {
        return (List<Inventario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Inventario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Inventario save(Inventario unInventario) {
        return repository.save(unInventario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
