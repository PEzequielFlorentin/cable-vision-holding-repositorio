package com.holding.holding_management_system.service;

import com.holding.holding_management_system.model.Area;
import com.holding.holding_management_system.repository.AreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    public List<Area> obtenerTodas() {
        return areaRepository.findAll();
    }

    public Area obtenerPorId(Long id) {
        return areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + id));
    }

    public Area guardar(Area area) {
        return areaRepository.save(area);
    }

    public Area actualizar(Long id, Area area) {
        Area existente = obtenerPorId(id);
        existente.setNombre(area.getNombre());
        existente.setDescripcion(area.getDescripcion());
        return areaRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!areaRepository.existsById(id)) {
            throw new RuntimeException("Área no encontrada con ID: " + id);
        }
        areaRepository.deleteById(id);
    }
}
