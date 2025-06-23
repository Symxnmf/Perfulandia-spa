package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Producto;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.ProductoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/productos")
@Tag(name = "Producto", description = "Operaciones relacionadas con productos")
public class ProductoRestController {

    @Autowired
    private ProductoService productoservice;

    @Operation(
        summary = "Obtener lista de productos",
        description = "Devuelve todos los registros de productos disponibles"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de productos obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
    )
    @GetMapping
    public List<Producto> mostrarProducto() {
        return productoservice.findByAll();
    }

    @Operation(
        summary = "Obtener producto por ID",
        description = "Obtiene el detalle de un producto específico"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Producto encontrado",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
    )
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> verProducto(@PathVariable Long id) {
        Optional<Producto> optionalProducto = productoservice.findById(id);
        if (optionalProducto.isPresent()) {
            return ResponseEntity.ok(optionalProducto.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear un nuevo producto",
        description = "Crea un registro de producto con los datos proporcionados"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Producto creado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
    )
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto unProducto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoservice.save(unProducto));
    }

    @Operation(
        summary = "Modificar un producto existente",
        description = "Actualiza los datos de un producto si existe"
    )
    @ApiResponse(responseCode = "200", description = "Producto modificado correctamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Producto unProducto) {
        Optional<Producto> optionalProducto = productoservice.findById(id);
        if (optionalProducto.isPresent()) {
            Producto productoExiste = optionalProducto.get();
            productoExiste.setNombre(unProducto.getNombre());
            productoExiste.setPrecio(unProducto.getPrecio());
            productoExiste.setStock(unProducto.getStock());
            productoExiste.setDescripcion(unProducto.getDescripcion());
            productoExiste.setCategoria(unProducto.getCategoria());
            Producto productoModificado = productoservice.save(productoExiste);
            return ResponseEntity.ok(productoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar un producto por ID",
        description = "Elimina un producto específico del sistema"
    )
    @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoservice.findById(id);
        if (productoOptional.isPresent()) {
            productoservice.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}