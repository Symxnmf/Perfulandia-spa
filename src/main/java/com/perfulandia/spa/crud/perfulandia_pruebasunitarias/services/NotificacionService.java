package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services;

import java.util.List;
import java.util.Optional;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Notificacion;

public interface NotificacionService {
    List<Notificacion> findByAll();

    Optional<Notificacion> findById(Long id);

    Notificacion save(Notificacion unnNotificacion);

    void deleteById(Long id);   

}
