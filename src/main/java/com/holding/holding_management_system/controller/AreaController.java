package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.dto.AreaDTO;
import com.holding.holding_management_system.service.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    // Obtener todas las áreas
    @GetMapping
    public ResponseEntity<List<AreaDTO>> obtenerAreas() {
        return ResponseEntity.ok(areaService.obtenerTodas());
    }

    // Obtener un área por ID
    @GetMapping("/{id}")
    public ResponseEntity<AreaDTO> obtenerAreaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.obtenerPorId(id));
    }

    // Crear una nueva área
    @PostMapping
    public ResponseEntity<AreaDTO> crearArea(@RequestBody AreaDTO areaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(areaService.guardar(areaDTO));
    }

    // Actualizar un área existente
    @PutMapping("/{id}")
    public ResponseEntity<AreaDTO> actualizarArea(@PathVariable Long id, @RequestBody AreaDTO areaDTO) {
        return ResponseEntity.ok(areaService.actualizar(id, areaDTO));
    }

    // Eliminar un área
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArea(@PathVariable Long id) {
        areaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
