package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.dto.EmpresaDTO;
import com.holding.holding_management_system.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> crearEmpresa(@RequestBody EmpresaDTO empresaDTO) {
        EmpresaDTO nuevaEmpresa = empresaService.guardar(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaEmpresa);
    }
}
