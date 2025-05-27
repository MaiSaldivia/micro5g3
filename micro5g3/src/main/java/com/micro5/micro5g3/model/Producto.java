package com.micro5.micro5g3.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue
    @Column(name = "id_producto", columnDefinition = "BINARY(16)")
    private UUID idProducto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(length = 255)
    private String descripcion;

    @Column(length = 100)
    private String marca;
}
