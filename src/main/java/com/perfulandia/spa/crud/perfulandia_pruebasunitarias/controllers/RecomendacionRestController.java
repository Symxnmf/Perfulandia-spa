package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Recomendacion;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.RecomendacionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/recomendaciones")
@Tag(name = "Recomendaciones", description = "Operaciones relacionadas con recomendaciones para clientes")
public class RecomendacionRestController {

    @Autowired
    private RecomendacionService recomendacionService;

    @Operation(
        summary = "Obtener lista de recomendaciones",
        description = "Devuelve todos los registros de recomendaciones"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de recomendaciones obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recomendacion.class))
    )
    @GetMapping
    public List<Recomendacion> mostrarRecomendaciones() {
        return recomendacionService.findByAll();
    }

    @Operation(
        summary = "Obtener recomendación por ID",
        description = "Obtiene una recomendación específica según su ID de cliente"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Recomendación encontrada",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recomendacion.class))
    )
    @ApiResponse(responseCode = "404", description = "Recomendación no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<?> verRecomendacion(@PathVariable Long id) {
        Optional<Recomendacion> optionalRecomendacion = recomendacionService.findById(id);
        if (optionalRecomendacion.isPresent()) {
            return ResponseEntity.ok(optionalRecomendacion.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear una nueva recomendación",
        description = "Registra una nueva recomendación en el sistema"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Recomendación creada exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Recomendacion.class))
    )
    @PostMapping
    public ResponseEntity<Recomendacion> crearRecomendacion(@RequestBody Recomendacion recomendacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recomendacionService.save(recomendacion));
    }

    @Operation(
        summary = "Modificar una recomendación existente",
        description = "Actualiza los datos de una recomendación existente"
    )
    @ApiResponse(responseCode = "200", description = "Recomendación modificada correctamente")
    @ApiResponse(responseCode = "404", description = "Recomendación no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarRecomendacion(@PathVariable Long id, @RequestBody Recomendacion recomendacion) {
        Optional<Recomendacion> optionalRecomendacion = recomendacionService.findById(id);
        if (optionalRecomendacion.isPresent()) {
            Recomendacion recomendacionExistente = optionalRecomendacion.get();
            recomendacionExistente.setProductosSugeridos(recomendacion.getProductosSugeridos());
            Recomendacion recomendacionModificada = recomendacionService.save(recomendacionExistente);
            return ResponseEntity.ok(recomendacionModificada);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar una recomendación",
        description = "Elimina una recomendación específica según su ID"
    )
    @ApiResponse(responseCode = "204", description = "Recomendación eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Recomendación no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRecomendacion(@PathVariable Long id) {
        Optional<Recomendacion> recomendacionOptional = recomendacionService.findById(id);
        if (recomendacionOptional.isPresent()) {
            recomendacionService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
