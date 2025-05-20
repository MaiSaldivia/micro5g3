package com.micro5.micro5g3.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    private UUID idInventario;

    private UUID idTienda;
    private int cantidadStock;
    private LocalDateTime fechaActualizacion;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    @JsonBackReference
    private Producto producto;
}
