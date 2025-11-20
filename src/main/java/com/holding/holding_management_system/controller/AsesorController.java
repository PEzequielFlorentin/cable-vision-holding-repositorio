package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.model.Asesor;
import com.holding.holding_management_system.service.AsesorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asesores")
public class AsesorController {

    private final AsesorService asesorService;

    public AsesorController(AsesorService asesorService) {
        this.asesorService = asesorService;
    }

    @GetMapping
    public List<Asesor> obtenerAsesores() {
        return asesorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Asesor obtenerAsesorPorId(@PathVariable Long id) {
        return asesorService.obtenerPorId(id);
    }

    @PostMapping
    public Asesor crearAsesor(@RequestBody Asesor asesor) {
        return asesorService.guardar(asesor);
    }

    @PutMapping("/{id}")
    public Asesor actualizarAsesor(@PathVariable Long id, @RequestBody Asesor asesor) {
        return asesorService.actualizar(id, asesor);
    }

    @DeleteMapping("/{id}")
    public void eliminarAsesor(@PathVariable Long id) {
        asesorService.eliminar(id);
    }
}
