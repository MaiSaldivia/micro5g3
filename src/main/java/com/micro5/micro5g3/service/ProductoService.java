package com.micro5.micro5g3.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro5.micro5g3.model.Producto;
import com.micro5.micro5g3.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Producto guardar(Producto p) {
        if (p.getIdProducto() == null) {
            p.setIdProducto(UUID.randomUUID());
        }
        return productoRepository.save(p);
    }

    public Producto buscarPorId(UUID id) {
        return productoRepository.findById(id).orElse(null);
    }
}


