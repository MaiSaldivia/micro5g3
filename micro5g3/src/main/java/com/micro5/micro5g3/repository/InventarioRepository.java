package com.micro5.micro5g3.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro5.micro5g3.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, UUID> {
    List<Inventario> findByIdTienda(UUID idTienda);
    List<Inventario> findByProducto_IdProducto(UUID idProducto);
    Optional<Inventario> findByProducto_IdProductoAndIdTienda(UUID idProducto, UUID idTienda);
}
