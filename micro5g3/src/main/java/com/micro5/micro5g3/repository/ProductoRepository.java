package com.micro5.micro5g3.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro5.micro5g3.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {
}
