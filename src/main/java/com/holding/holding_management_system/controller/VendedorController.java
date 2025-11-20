package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.model.Vendedor;
import com.holding.holding_management_system.service.VendedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public List<Vendedor> obtenerVendedores() {
        return vendedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Vendedor obtenerVendedorPorId(@PathVariable Long id) {
        return vendedorService.obtenerPorId(id);
    }

    @PostMapping
    public Vendedor crearVendedor(@RequestBody Vendedor vendedor) {
        return vendedorService.guardar(vendedor);
    }

    @PutMapping("/{id}")
    public Vendedor actualizarVendedor(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        return vendedorService.actualizar(id, vendedor);
    }

    @DeleteMapping("/{id}")
    public void eliminarVendedor(@PathVariable Long id) {
        vendedorService.eliminar(id);
    }
}