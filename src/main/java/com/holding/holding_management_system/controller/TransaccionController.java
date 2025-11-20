package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.model.Transaccion;
import com.holding.holding_management_system.service.TransaccionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping
    public List<Transaccion> obtenerTodas() {
        return transaccionService.obtenerTodas();
    }

    @PostMapping
    public Transaccion guardar(@RequestBody Transaccion transaccion) {
        return transaccionService.guardar(transaccion);
    }
}
