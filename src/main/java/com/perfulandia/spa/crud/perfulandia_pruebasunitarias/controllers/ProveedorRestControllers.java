package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Proveedor;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.ProveedorService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/proveedores")
@Tag(name = "Proveedores", description = "Operaciones relacionadas con proveedores")
public class ProveedorRestControllers {

    @Autowired
    private ProveedorService service;

    @Operation(
        summary = "Listar todos los proveedores",
        description = "Devuelve una lista de todos los proveedores registrados"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista obtenida exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class))
    )
    @GetMapping
    public List<Proveedor> List() {
        return service.findByAll();
    }

    @Operation(
        summary = "Obtener proveedor por ID",
        description = "Devuelve los datos de un proveedor específico si existe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Proveedor encontrado",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class))
    )
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = service.findById(id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear nuevo proveedor",
        description = "Guarda un nuevo proveedor en la base de datos"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Proveedor creado exitosamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class))
    )
    @PostMapping
    public ResponseEntity<Proveedor> crear(@RequestBody Proveedor unProveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unProveedor));
    }

    @Operation(
        summary = "Actualizar proveedor existente",
        description = "Modifica los datos de un proveedor si existe el ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Proveedor actualizado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class))
    )
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Proveedor unProveedor) {
        Optional<Proveedor> proveedorOptional = service.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedorExistente = proveedorOptional.get();
            proveedorExistente.setDireccion(unProveedor.getDireccion());
            proveedorExistente.setNombreEmpresa(unProveedor.getNombreEmpresa());
            proveedorExistente.setContacto(unProveedor.getContacto());
            Proveedor proveedorModificado = service.save(proveedorExistente);
            return ResponseEntity.ok(proveedorModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar proveedor",
        description = "Elimina un proveedor por su ID si existe"
    )
    @ApiResponse(responseCode = "200", description = "Proveedor eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Proveedor unProveedor = new Proveedor();
        unProveedor.setId(id);
        Optional<Proveedor> proveedorOptional = service.delete(unProveedor);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
}
