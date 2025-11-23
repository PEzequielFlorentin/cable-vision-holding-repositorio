package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.dto.AreaDTO;
import com.holding.holding_management_system.dto.EmpresaDTO;
import com.holding.holding_management_system.dto.VendedorDTO;
import com.holding.holding_management_system.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }


    @PostMapping("/{id}/asignar-areas")
    public ResponseEntity<Void> asignarAreasAEmpresa(
            @PathVariable Long id,
            @RequestBody List<Long> areaIds) {
        empresaService.asignarAreas(id, areaIds);
        return ResponseEntity.ok().build();
    }

    // Endpoint para obtener las áreas de una empresa
    @GetMapping("/{id}/areas")
    public ResponseEntity<List<AreaDTO>> obtenerAreasDeEmpresa(@PathVariable Long id) {
        List<AreaDTO> areas = empresaService.obtenerAreasDeEmpresa(id);
        return ResponseEntity.ok(areas);
    }

    // Endpoint para eliminar una empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint para obtener los vendedores de una empresa
    @GetMapping("/{id}/vendedores")
    public ResponseEntity<List<VendedorDTO>> obtenerVendedoresDeEmpresa(@PathVariable Long id) {
        List<VendedorDTO> vendedores = empresaService.obtenerVendedoresDeEmpresa(id);
        return ResponseEntity.ok(vendedores);
    }

    // Endpoint para actualizar una empresa
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> actualizarEmpresa(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO empresaActualizada = empresaService.actualizarEmpresa(id, empresaDTO);
        return ResponseEntity.ok(empresaActualizada);
    }

    @GetMapping
    public List<EmpresaDTO> obtenerEmpresas() {
        return empresaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public EmpresaDTO obtenerEmpresaPorId(@PathVariable Long id) {
        return empresaService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> crearEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO nuevaEmpresa = empresaService.guardar(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
    }
}
