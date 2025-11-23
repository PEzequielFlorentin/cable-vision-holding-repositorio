package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.dto.AsesorDTO;
import com.holding.holding_management_system.dto.AsignacionDTO;
import com.holding.holding_management_system.service.AsesorService;
import com.holding.holding_management_system.service.AsignacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asesores")
public class AsesorController {

    private final AsesorService asesorService;
    private final AsignacionService asignacionService; // Inyección del servicio AsignacionService

    public AsesorController(AsesorService asesorService, AsignacionService asignacionService) {
        this.asesorService = asesorService;
        this.asignacionService = asignacionService;
    }

    // Crear un nuevo asesor
    @PostMapping
    public ResponseEntity<AsesorDTO> crearAsesor(@RequestBody AsesorDTO asesorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(asesorService.guardar(asesorDTO));
    }

    // Obtener todos los asesores
    @GetMapping
    public ResponseEntity<List<AsesorDTO>> obtenerAsesores() {
        return ResponseEntity.ok(asesorService.obtenerTodos());
    }

    // Obtener un asesor por ID
    @GetMapping("/{id}")
    public ResponseEntity<AsesorDTO> obtenerAsesorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(asesorService.obtenerPorId(id));
    }

    // Actualizar un asesor existente
    @PutMapping("/{id}")
    public ResponseEntity<AsesorDTO> actualizarAsesor(@PathVariable Long id, @RequestBody AsesorDTO asesorDTO) {
        return ResponseEntity.ok(asesorService.actualizar(id, asesorDTO));
    }

    // Eliminar un asesor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsesor(@PathVariable Long id) {
        asesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Asignar un asesor a una empresa y un área
    @PostMapping("/{id}/asignar")
    public ResponseEntity<Void> asignarAsesor(
            @PathVariable Long id,
            @RequestBody AsignacionDTO asignacionDTO) {
        asignacionService.asignarAsesor(id, asignacionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}