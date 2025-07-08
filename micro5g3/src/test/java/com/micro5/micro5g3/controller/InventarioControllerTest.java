package com.micro5.micro5g3.controller;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.micro5.micro5g3.model.Inventario;
import com.micro5.micro5g3.model.Producto;
import com.micro5.micro5g3.service.InventarioService;

public class InventarioControllerTest {

    @Mock
    private InventarioService inventarioService;

    @InjectMocks
    private InventarioController inventarioController;

    private UUID productoId;
    private UUID inventarioId;
    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productoId = UUID.randomUUID();
        inventarioId = UUID.randomUUID();

        Producto producto = new Producto(productoId, "Producto A", "Alimentos", "desc", "marca");
        inventario = new Inventario(inventarioId, 1, producto, 10, LocalDateTime.now());
    }

    @Test
    public void testAgregarProducto_OK() {
        when(inventarioService.agregarProducto(1, productoId, 10)).thenReturn(inventario);

        ResponseEntity<Inventario> response = inventarioController.agregarProducto(1, productoId, 10);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(inventario, response.getBody());
    }

    @Test
    public void testAgregarProducto_Fallo() {
        when(inventarioService.agregarProducto(1, productoId, 10)).thenReturn(null);

        ResponseEntity<Inventario> response = inventarioController.agregarProducto(1, productoId, 10);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testAjustarStock_OK() {
        when(inventarioService.ajustarStock(inventarioId, 20)).thenReturn(true);

        ResponseEntity<Void> response = inventarioController.ajustarStock(inventarioId, 20);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testAjustarStock_Fallo() {
        when(inventarioService.ajustarStock(inventarioId, 20)).thenReturn(false);

        ResponseEntity<Void> response = inventarioController.ajustarStock(inventarioId, 20);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testEliminar_OK() {
        when(inventarioService.eliminarProducto(inventarioId)).thenReturn(true);

        ResponseEntity<Void> response = inventarioController.eliminar(inventarioId);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testEliminar_Fallo() {
        when(inventarioService.eliminarProducto(inventarioId)).thenReturn(false);

        ResponseEntity<Void> response = inventarioController.eliminar(inventarioId);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testBuscarPorTienda_ConResultados() {
        when(inventarioService.buscarPorTienda(1)).thenReturn(List.of(inventario));

        ResponseEntity<List<Inventario>> response = inventarioController.buscarPorTienda(1);

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testBuscarPorTienda_SinResultados() {
        when(inventarioService.buscarPorTienda(1)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Inventario>> response = inventarioController.buscarPorTienda(1);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testObtenerStock() {
        when(inventarioService.obtenerStock(productoId, 1)).thenReturn(15);

        ResponseEntity<Integer> response = inventarioController.obtenerStock(productoId, 1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(15, response.getBody());
    }
}