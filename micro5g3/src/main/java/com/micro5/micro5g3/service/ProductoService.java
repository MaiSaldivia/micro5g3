package com.micro5.micro5g3.service;

import com.micro5.micro5g3.model.Producto;
import com.micro5.micro5g3.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(UUID idProducto) {
        return productoRepository.findById(idProducto);
    }

    public Producto guardar(Producto producto) {
        producto.setIdProducto(UUID.randomUUID());
        return productoRepository.save(producto);
    }
}
