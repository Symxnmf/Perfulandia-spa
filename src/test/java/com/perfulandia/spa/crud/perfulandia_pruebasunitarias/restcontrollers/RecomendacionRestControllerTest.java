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
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.controllers.RecomendacionRestController;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.entities.Recomendacion;
import com.perfulandia.spa.crud.perfulandia_pruebasunitarias.services.RecomendacionService;

@WebMvcTest(RecomendacionRestController.class)
public class RecomendacionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecomendacionService recomendacionService;

    @Test
    public void verRecomendacionesTest() throws Exception {
        when(recomendacionService.findByAll()).thenReturn(List.of());
        mockMvc.perform(get("/api/recomendaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verUnaRecomendacionTest() {
        Recomendacion recomendacion = new Recomendacion(1L, "Producto1,Producto2");
        try {
            when(recomendacionService.findById(1L)).thenReturn(Optional.of(recomendacion));
            mockMvc.perform(get("/api/recomendaciones/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception ex) {
            fail("El test lanzó una excepción: " + ex.getMessage());
        }
    }

    @Test
    public void recomendacionNoExisteTest() throws Exception {
        when(recomendacionService.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/recomendaciones/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearRecomendacionTest() throws Exception {
        Recomendacion nuevo = new Recomendacion(null, "ProductoA,ProductoB");
        Recomendacion guardado = new Recomendacion(3L, "ProductoA,ProductoB");
        when(recomendacionService.save(any(Recomendacion.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/recomendaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated());
    }
}
