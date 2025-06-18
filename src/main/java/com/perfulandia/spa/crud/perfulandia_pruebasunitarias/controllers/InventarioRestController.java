package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Inventario;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.InventarioService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/inventario")
@Tag(name = "Inventario", description = "Operaciones relacionadas con inventario")
public class InventarioRestController {

    @Autowired
    private InventarioService service;

    @Operation(
        summary = "Obtener lista de inventarios",
        description = "Devuelve todos los registros de inventario disponibles"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de inventarios obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))
    )
    @GetMapping
    public List<Inventario> listar() {
        return service.findByAll();
    }

    @Operation(
        summary = "Obtener inventario por ID",
        description = "Obtiene el detalle de un registro específico de inventario"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Inventario encontrado",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))
    )
    @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Inventario> inventarioOptional = service.findById(id);
        if (inventarioOptional.isPresent()) {
            return ResponseEntity.ok(inventarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear un nuevo inventario",
        description = "Crea un registro de inventario con los datos proporcionados"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Inventario creado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Inventario.class))
    )
    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario unInventario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unInventario));
    }

    @Operation(
        summary = "Modificar un inventario existente",
        description = "Actualiza los datos de un registro de inventario si existe"
    )
    @ApiResponse(responseCode = "200", description = "Inventario modificado correctamente")
    @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Inventario unInventario) {
        Optional<Inventario> inventarioOptional = service.findById(id);
        if (inventarioOptional.isPresent()) {
            Inventario inventarioExistente = inventarioOptional.get();
            inventarioExistente.setProducto(unInventario.getProducto());
            inventarioExistente.setCantidad(unInventario.getCantidad());
            inventarioExistente.setUbicacion(unInventario.getUbicacion());
            inventarioExistente.setFechaIngreso(unInventario.getFechaIngreso());
            Inventario inventarioModificado = service.save(inventarioExistente);
            return ResponseEntity.ok(inventarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar inventario por ID",
        description = "Elimina un registro de inventario específico del sistema"
    )
    @ApiResponse(responseCode = "200", description = "Inventario eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Inventario> inventarioOptional = service.findById(id);
        if (inventarioOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
