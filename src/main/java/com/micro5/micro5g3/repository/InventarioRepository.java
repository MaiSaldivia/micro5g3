package com.micro5.micro5g3.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro5.micro5g3.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, UUID> {
}

