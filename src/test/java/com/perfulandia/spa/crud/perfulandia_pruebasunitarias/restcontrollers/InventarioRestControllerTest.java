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
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers.InventarioRestController;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Inventario;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.InventarioService;

@WebMvcTest(InventarioRestController.class)
public class InventarioRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InventarioService inventarioService;

    @Test
    public void verInventariosTest() throws Exception {
        when(inventarioService.findByAll()).thenReturn(List.of());
        mockmvc.perform(get("/api/inventario")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnInventarioTest() {
        Inventario unInventario = new Inventario(1L, "Producto Test", 100, "Bodega Central", 20250618);
        try {
            when(inventarioService.findById(1L)).thenReturn(Optional.of(unInventario));
            mockmvc.perform(get("/api/inventario/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El testing lanzó un error: " + ex.getMessage());
        }
    }

    @Test
    public void inventarioNoExisteTest() throws Exception {
        when(inventarioService.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/inventario/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearInventarioTest() throws Exception {
        Inventario unInventario = new Inventario(null, "Producto Nuevo", 50, "Bodega Norte", 20250618);
        Inventario otroInventario = new Inventario(3L, "Producto Nuevo", 50, "Bodega Norte", 20250618);
        when(inventarioService.save(any(Inventario.class))).thenReturn(otroInventario);

        mockmvc.perform(post("/api/inventario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unInventario)))
                .andExpect(status().isCreated());
    }
}