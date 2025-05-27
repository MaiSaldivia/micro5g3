package com.micro5.micro5g3.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.micro5.micro5g3.model.Inventario;
import com.micro5.micro5g3.service.InventarioService;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping("/agregar")
    public ResponseEntity<Inventario> agregarProducto(@RequestParam UUID idTienda,
                                                      @RequestParam UUID idProducto,
                                                      @RequestParam int cantidad) {
        Inventario inv = inventarioService.agregarProducto(idTienda, idProducto, cantidad);
        return inv != null ? new ResponseEntity<>(inv, HttpStatus.CREATED)
                           : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/ajustar/{idInventario}")
    public ResponseEntity<Void> ajustarStock(@PathVariable UUID idInventario,
                                             @RequestParam int cantidad) {
        boolean ok = inventarioService.ajustarStock(idInventario, cantidad);
        return ok ? new ResponseEntity<>(HttpStatus.OK)
                  : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{idInventario}")
    public ResponseEntity<Void> eliminar(@PathVariable UUID idInventario) {
        boolean ok = inventarioService.eliminarProducto(idInventario);
        return ok ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                  : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/tienda/{idTienda}")
    public ResponseEntity<List<Inventario>> buscarPorTienda(@PathVariable UUID idTienda) {
        List<Inventario> lista = inventarioService.buscarPorTienda(idTienda);
        return lista.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                               : new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/stock")
    public ResponseEntity<Integer> obtenerStock(@RequestParam UUID idProducto,
                                                @RequestParam UUID idTienda) {
        int stock = inventarioService.obtenerStock(idProducto, idTienda);
        return new ResponseEntity<>(stock, HttpStatus.OK);
    }
}
