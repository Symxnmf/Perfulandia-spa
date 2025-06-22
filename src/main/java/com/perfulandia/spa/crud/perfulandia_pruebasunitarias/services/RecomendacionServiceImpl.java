package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Recomendacion;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.repository.RecomendacionRepository;

@Service
public class RecomendacionServiceImpl implements RecomendacionService {

    @Autowired
    private RecomendacionRepository recomendacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Recomendacion> findByAll() {
        return (List<Recomendacion>) recomendacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Recomendacion> findById(Long id) {
        return recomendacionRepository.findById(id);
    }

    @Override
    @Transactional
    public Recomendacion save(Recomendacion unRecomendacion) {
        return recomendacionRepository.save(unRecomendacion);
    }

    @Override
    @Transactional
    public Optional<Recomendacion> delete(Long id) {
        Optional<Recomendacion> optional = recomendacionRepository.findById(id);
        optional.ifPresent(r -> recomendacionRepository.deleteById(id));
        return optional;
    }
}
