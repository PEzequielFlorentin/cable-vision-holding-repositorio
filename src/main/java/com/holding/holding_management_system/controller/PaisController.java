package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.dto.EmpresaDTO;
import com.holding.holding_management_system.dto.PaisDTO;
import com.holding.holding_management_system.service.PaisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    private final PaisService paisService;

    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    @GetMapping
    public ResponseEntity<List<PaisDTO>> obtenerPaises() {
        return ResponseEntity.ok(paisService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> obtenerPaisPorId(@PathVariable Long id) {
        return ResponseEntity.ok(paisService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PaisDTO> crearPais(@RequestBody PaisDTO paisDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paisService.guardar(paisDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisDTO> actualizarPais(@PathVariable Long id, @RequestBody PaisDTO paisDTO) {
        return ResponseEntity.ok(paisService.actualizar(id, paisDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPais(@PathVariable Long id) {
        paisService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    // Consultar las empresas por país
    @GetMapping("/{id}/empresas")
    public ResponseEntity<List<EmpresaDTO>> obtenerEmpresasPorPais(@PathVariable Long id) {
        List<EmpresaDTO> empresas = paisService.obtenerEmpresasPorPais(id);
        return ResponseEntity.ok(empresas);
    }
}