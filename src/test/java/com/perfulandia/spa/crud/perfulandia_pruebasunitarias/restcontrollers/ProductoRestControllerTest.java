package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.restcontrollers;


import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers.ProductoRestControllers;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Producto;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.ProductoService;

@WebMvcTest(ProductoRestControllers.class)
public class ProductoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductoService productoService;

    @Test
    public void verProductosTest() throws Exception {
        when(productoService.findByAll()).thenReturn(List.of());
        mockMvc.perform(get("/api/productos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnProductoTest() {
        Producto producto = new Producto(1L, "Café", 2000, 10, "Café molido", "Bebida");
        try {
            when(productoService.findById(1L)).thenReturn(Optional.of(producto));
            mockMvc.perform(get("/api/productos/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El test lanzó una excepción: " + ex.getMessage());
        }
    }

    @Test
    public void productoNoExisteTest() throws Exception {
        when(productoService.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/productos/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProductoTest() throws Exception {
        Producto nuevo = new Producto(null, "Teclado", 15000, 25, "Teclado mecánico", "Electrónica");
        Producto guardado = new Producto(3L, "Teclado", 15000, 25, "Teclado mecánico", "Electrónica");
        when(productoService.save(any(Producto.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated());
    }
}
