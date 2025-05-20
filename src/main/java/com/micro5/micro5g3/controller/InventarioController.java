package com.micro5.micro5g3.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.micro5.micro5g3.model.Inventario;
import com.micro5.micro5g3.model.Producto;
import com.micro5.micro5g3.service.InventarioService;
import com.micro5.micro5g3.service.ProductoService;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAll() {
        List<Inventario> inventario = inventarioService.listarTodos();
        if (inventario.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventario> postInventario(@RequestBody Inventario inv) {
        UUID idProducto = inv.getProducto().getIdProducto();
        Producto p = productoService.buscarPorId(idProducto);
        if (p == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        inv.setProducto(p);
        Inventario nuevo = inventarioService.guardar(inv);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
