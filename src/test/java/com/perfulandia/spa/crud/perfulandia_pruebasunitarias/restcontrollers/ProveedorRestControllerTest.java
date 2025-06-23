package com.perfulandia.spa.crud.perfulandia_pruebasunitarias.restcontrollers;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers.ProveedorRestController;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Proveedor;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.ProveedorService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProveedorRestController.class)
public class ProveedorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProveedorService proveedorService;

    @Test
    void listarProveedoresTest() throws Exception {
        when(proveedorService.findByAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/proveedores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void verProveedorPorIdExistenteTest() throws Exception {
        Proveedor proveedor = new Proveedor(1L, "Dirección Test", "Empresa Test", "Contacto Test");
        when(proveedorService.findById(1L)).thenReturn(Optional.of(proveedor));

        mockMvc.perform(get("/api/proveedores/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void verProveedorPorIdNoExistenteTest() throws Exception {
        when(proveedorService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/proveedores/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearProveedorTest() throws Exception {
        Proveedor nuevoProveedor = new Proveedor(null, "Nueva Dirección", "Nueva Empresa", "Nuevo Contacto");
        Proveedor proveedorGuardado = new Proveedor(1L, "Nueva Dirección", "Nueva Empresa", "Nuevo Contacto");

        when(proveedorService.save(any(Proveedor.class))).thenReturn(proveedorGuardado);

        mockMvc.perform(post("/api/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoProveedor)))
                .andExpect(status().isCreated());
    }

    @Test
    void modificarProveedorExistenteTest() throws Exception {
        Proveedor existente = new Proveedor(1L, "Vieja Dirección", "Vieja Empresa", "Viejo Contacto");
        Proveedor actualizado = new Proveedor(1L, "Dirección Actualizada", "Empresa Actualizada", "Contacto Actualizado");

        when(proveedorService.findById(1L)).thenReturn(Optional.of(existente));
        when(proveedorService.save(any(Proveedor.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/proveedores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk());
    }

    @Test
    void modificarProveedorInexistenteTest() throws Exception {
        Proveedor modificado = new Proveedor(99L, "Dirección", "Empresa", "Contacto");

        when(proveedorService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/proveedores/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modificado)))
                .andExpect(status().isNotFound());
    }

    @Test
    void eliminarProveedorExistenteTest() throws Exception {
        Proveedor proveedor = new Proveedor(1L, "Dirección", "Empresa", "Contacto");

        when(proveedorService.delete(any(Proveedor.class))).thenReturn(Optional.of(proveedor));

        mockMvc.perform(delete("/api/proveedores/1"))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarProveedorInexistenteTest() throws Exception {
        when(proveedorService.delete(any(Proveedor.class))).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/proveedores/99"))
                .andExpect(status().isNotFound());
    }
}