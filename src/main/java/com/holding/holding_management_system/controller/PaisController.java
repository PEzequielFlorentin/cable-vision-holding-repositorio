package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.model.Pais;
import com.holding.holding_management_system.service.PaisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {

    private final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping
    public List<Pais> obtenerPaises() {
        return paisService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pais obtenerPaisPorId(@PathVariable Long id) {
        return paisService.obtenerPorId(id);
    }

    @PostMapping
    public Pais crearPais(@RequestBody Pais pais) {
        return paisService.guardar(pais);
    }

    @PutMapping("/{id}")
    public Pais actualizarPais(@PathVariable Long id, @RequestBody Pais pais) {
        return paisService.actualizar(id, pais);
    }

    @DeleteMapping("/{id}")
    public void eliminarPais(@PathVariable Long id) {
        paisService.eliminar(id);
    }
}
