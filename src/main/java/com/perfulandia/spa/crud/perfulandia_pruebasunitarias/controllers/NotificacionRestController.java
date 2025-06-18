package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Notificacion;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.NotificacionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/notificacion")
@Tag(name = "Notificaciones", description = "Operaciones relacionadas con notificaciones")
public class NotificacionRestController {

    @Autowired
    private NotificacionService service;

    @Operation(
        summary = "Listar todas las notificaciones",
        description = "Devuelve la lista completa de notificaciones almacenadas"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de notificaciones obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))
    )
    @GetMapping
    public List<Notificacion> List(){
        return service.findByAll();
    }

    @Operation(
        summary = "Obtener una notificación por ID",
        description = "Busca una notificación específica mediante su ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Notificación encontrada",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))
    )
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Notificacion> notificacionOptional = service.findById(id);
        if (notificacionOptional.isPresent()){
            return ResponseEntity.ok(notificacionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear una nueva notificación",
        description = "Permite crear y almacenar una nueva notificación"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Notificación creada correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))
    )
    @PostMapping
    public ResponseEntity<Notificacion> crear (@RequestBody Notificacion unnNotificacion){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unnNotificacion));
    }
    
    @Operation(
        summary = "Actualizar una notificación existente",
        description = "Modifica los datos de una notificación identificada por su ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Notificación modificada correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))
    )
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Notificacion unnNotificacion){
        Optional <Notificacion> notificacionOptional = service.findById(id);
        if(notificacionOptional.isPresent()){
            Notificacion notificacionexistente = notificacionOptional.get();
            notificacionexistente.setTipo(unnNotificacion.getTipo());
            notificacionexistente.setDestinatario(unnNotificacion.getDestinatario());
            notificacionexistente.setMensaje(unnNotificacion.getMensaje());
            notificacionexistente.setFecha_envio(unnNotificacion.getFecha_envio());
            notificacionexistente.setEstado(unnNotificacion.getEstado());
            Notificacion notificacionmodificado = service.save(notificacionexistente);
            return ResponseEntity.ok(notificacionmodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar una notificación por ID",
        description = "Elimina una notificación específica del sistema usando su ID"
    )
    @ApiResponse(responseCode = "200", description = "Notificación eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Notificacion> notificacionOptional = service.findById(id);
        if (notificacionOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
