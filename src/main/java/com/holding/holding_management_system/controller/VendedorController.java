package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.dto.VendedorDTO;
import com.holding.holding_management_system.model.Vendedor;
import com.holding.holding_management_system.service.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    // Endpoint para eliminar un vendedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable Long id) {
        vendedorService.eliminarVendedor(id);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoint para actualizar un vendedor
    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> actualizarVendedor(@PathVariable Long id, @RequestBody VendedorDTO vendedorDTO) {
        VendedorDTO vendedorActualizado = vendedorService.actualizarVendedor(id, vendedorDTO);
        return ResponseEntity.ok(vendedorActualizado);
    }

    // Endpoint para consultar todos los vendedores
    @GetMapping
    public ResponseEntity<List<VendedorDTO>> obtenerTodos() {
        List<VendedorDTO> vendedores = vendedorService.obtenerTodos();
        return ResponseEntity.ok(vendedores);
    }

    // Endpoint para consultar los vendedores captados por un vendedor
    @GetMapping("/{id}/captados")
    public ResponseEntity<List<VendedorDTO>> obtenerVendedoresCaptados(@PathVariable Long id) {
        List<VendedorDTO> vendedoresCaptados = vendedorService.obtenerVendedoresCaptados(id);
        return ResponseEntity.ok(vendedoresCaptados);
    }

    // Endpoint para consultar un vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> obtenerPorId(@PathVariable Long id) {
        VendedorDTO vendedorDTO = vendedorService.obtenerPorId(id);
        return ResponseEntity.ok(vendedorDTO);
    }

    @GetMapping("/empresa/{empresaId}/piramide")
    public ResponseEntity<List<VendedorDTO>> obtenerPiramidePorEmpresa(@PathVariable Long empresaId) {
        List<VendedorDTO> piramide = vendedorService.obtenerPiramidePorEmpresa(empresaId);
        return ResponseEntity.ok(piramide);
    }
    
    // Endpoint para crear un vendedor
    @PostMapping
    public ResponseEntity<Vendedor> guardar(@RequestBody VendedorDTO vendedorDTO) {
        // Llama al servicio para guardar el vendedor y obtener el DTO
        VendedorDTO nuevoVendedorDTO = vendedorService.guardar(vendedorDTO);

        // Convierte el DTO a la entidad Vendedor
        Vendedor nuevoVendedor = new Vendedor();
        nuevoVendedor.setId(nuevoVendedorDTO.getId());
        nuevoVendedor.setCodigo(nuevoVendedorDTO.getCodigo());
        nuevoVendedor.setNombre(nuevoVendedorDTO.getNombre());
        nuevoVendedor.setDireccion(nuevoVendedorDTO.getDireccion());
        nuevoVendedor.setFechaCaptacion(nuevoVendedorDTO.getFechaCaptacion());
        // Nota: Si necesitas asignar la empresa, hazlo aquí

        // Devuelve el ResponseEntity con la entidad Vendedor
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVendedor);
    }
}