package com.micro5.micro5g3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro5.micro5g3.model.Inventario;
import com.micro5.micro5g3.model.Producto;
import com.micro5.micro5g3.repository.InventarioRepository;
import com.micro5.micro5g3.repository.ProductoRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public Inventario agregarProducto(int idTienda, UUID idProducto, int cantidad) {
        Optional<Producto> productoOpt = productoRepository.findById(idProducto);
        if (productoOpt.isEmpty()) return null;

        Inventario inv = new Inventario();
        inv.setIdInventario(UUID.randomUUID());
        inv.setIdTienda(idTienda);
        inv.setProducto(productoOpt.get());
        inv.setCantidadStock(cantidad);
        inv.setFechaActualizacion(LocalDateTime.now());

        return inventarioRepository.save(inv);
    }

    public boolean ajustarStock(UUID idInventario, int cantidad) {
        Optional<Inventario> invOpt = inventarioRepository.findById(idInventario);
        if (invOpt.isPresent()) {
            Inventario inv = invOpt.get();
            inv.setCantidadStock(cantidad);
            inv.setFechaActualizacion(LocalDateTime.now());
            inventarioRepository.save(inv);
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(UUID idInventario) {
        Optional<Inventario> invOpt = inventarioRepository.findById(idInventario);
        if (invOpt.isPresent()) {
            inventarioRepository.deleteById(idInventario);
            return true;
        }
        return false;
    }

    public List<Inventario> buscarPorTienda(int idTienda) {
        return inventarioRepository.findByIdTienda(idTienda);
    }

    public Optional<Inventario> obtenerInventario(UUID idProducto, int idTienda) {
        return inventarioRepository.findByProducto_IdProductoAndIdTienda(idProducto, idTienda);
    }

    public int obtenerStock(UUID idProducto, int idTienda) {
        return obtenerInventario(idProducto, idTienda)
                .map(Inventario::getCantidadStock)
                .orElse(0);
    }
}

