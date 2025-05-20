package com.micro5.micro5g3.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

@Id
private UUID idProducto;

private String nombre;
private String categoria;
private String descripcion;
private String marca;

@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonManagedReference
private List<Inventario> inventarios;

}
