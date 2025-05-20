package com.micro5.micro5g3.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro5.micro5g3.model.Inventario;
import com.micro5.micro5g3.repository.InventarioRepository;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    public Inventario guardar(Inventario i) {
        if (i.getIdInventario() == null) {
            i.setIdInventario(UUID.randomUUID());
        }
        i.setFechaActualizacion(LocalDateTime.now());
        return inventarioRepository.save(i);
    }

    public void eliminar(UUID idInventario) {
        inventarioRepository.deleteById(idInventario);
    }
}
