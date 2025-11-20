package com.holding.holding_management_system.controller;

import com.holding.holding_management_system.model.Area;
import com.holding.holding_management_system.service.AreaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @GetMapping
    public List<Area> obtenerAreas() {
        return areaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Area obtenerAreaPorId(@PathVariable Long id) {
        return areaService.obtenerPorId(id);
    }

    @PostMapping
    public Area crearArea(@RequestBody Area area) {
        return areaService.guardar(area);
    }

    @PutMapping("/{id}")
    public Area actualizarArea(@PathVariable Long id, @RequestBody Area area) {
        return areaService.actualizar(id, area);
    }

    @DeleteMapping("/{id}")
    public void eliminarArea(@PathVariable Long id) {
        areaService.eliminar(id);
    }
}
