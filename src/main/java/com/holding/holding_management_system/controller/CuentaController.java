package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.model.Cuenta;
import com.holding.holding_management_system.service.CuentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public List<Cuenta> obtenerTodas() {
        return cuentaService.obtenerTodas();
    }

    @PostMapping
    public Cuenta guardar(@RequestBody Cuenta cuenta) {
        return cuentaService.guardar(cuenta);
    }
}
