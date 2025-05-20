package com.micro5.micro5g3.repository;

import com.micro5.micro5g3.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProductoRepository extends JpaRepository<Producto, Long> {
    
}


