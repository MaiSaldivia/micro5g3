package com.micro5.micro5g3.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @Column(name = "id_inventario", columnDefinition = "BINARY(16)")
    private UUID idInventario;

    @Column(name = "id_tienda", columnDefinition = "BINARY(16)", nullable = false)
    private UUID idTienda;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Column(name = "cantidad_stock", nullable = false)
    private int cantidadStock;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;
}
