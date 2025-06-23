package com.micro5.micro5g3.service;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.micro5.micro5g3.model.Inventario;
import com.micro5.micro5g3.model.Producto;
import com.micro5.micro5g3.repository.InventarioRepository;
import com.micro5.micro5g3.repository.ProductoRepository;

public class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private InventarioService inventarioService;

    public InventarioServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgregarProducto() {
        UUID idProducto = UUID.randomUUID();
        UUID idTienda = UUID.randomUUID();

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setNombre("Laptop");

        when(productoRepository.findById(idProducto)).thenReturn(Optional.of(producto));
        when(inventarioRepository.save(any(Inventario.class))).thenAnswer(i -> i.getArgument(0));

        Inventario resultado = inventarioService.agregarProducto(idTienda, idProducto, 10);

        assertNotNull(resultado);
        assertEquals(10, resultado.getCantidadStock());
        assertEquals(producto, resultado.getProducto());
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    void testAjustarStock() {
        UUID idInventario = UUID.randomUUID();

        Inventario inv = new Inventario();
        inv.setIdInventario(idInventario);
        inv.setCantidadStock(5);

        when(inventarioRepository.findById(idInventario)).thenReturn(Optional.of(inv));

        boolean resultado = inventarioService.ajustarStock(idInventario, 20);

        assertTrue(resultado);
        assertEquals(20, inv.getCantidadStock());
        verify(inventarioRepository, times(1)).save(inv);
    }

    @Test
    void testEliminarProducto() {
        UUID idInventario = UUID.randomUUID();

        when(inventarioRepository.findById(idInventario)).thenReturn(Optional.of(new Inventario()));

        boolean resultado = inventarioService.eliminarProducto(idInventario);

        assertTrue(resultado);
        verify(inventarioRepository, times(1)).deleteById(idInventario);
    }

    @Test
    void testObtenerStockConInventario() {
        UUID idProducto = UUID.randomUUID();
        UUID idTienda = UUID.randomUUID();

        Inventario inv = new Inventario();
        inv.setCantidadStock(99);

        when(inventarioRepository.findByProducto_IdProductoAndIdTienda(idProducto, idTienda))
                .thenReturn(Optional.of(inv));

        int stock = inventarioService.obtenerStock(idProducto, idTienda);

        assertEquals(99, stock);
    }

    @Test
    void testObtenerStockSinInventario() {
        UUID idProducto = UUID.randomUUID();
        UUID idTienda = UUID.randomUUID();

        when(inventarioRepository.findByProducto_IdProductoAndIdTienda(idProducto, idTienda))
                .thenReturn(Optional.empty());

        int stock = inventarioService.obtenerStock(idProducto, idTienda);

        assertEquals(0, stock);
    }
}

