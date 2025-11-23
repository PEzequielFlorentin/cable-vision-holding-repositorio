package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.AreaDTO;
import com.holding.holding_management_system.model.Area;
import com.holding.holding_management_system.model.Empresa;
import com.holding.holding_management_system.repository.AreaRepository;
import com.holding.holding_management_system.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AreaService {

    private final AreaRepository areaRepository;
    private final EmpresaRepository empresaRepository;

    public AreaService(AreaRepository areaRepository, EmpresaRepository empresaRepository) {
        this.areaRepository = areaRepository;
        this.empresaRepository = empresaRepository;
    }

    // Obtener todas las áreas
    public List<AreaDTO> obtenerTodas() {
        List<Area> areas = areaRepository.findAll();
        return areas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener un área por ID
    public AreaDTO obtenerPorId(Long id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + id));
        return convertToDTO(area);
    }

    // Crear una nueva área
    public AreaDTO guardar(AreaDTO areaDTO) {
        Area area = convertToEntity(areaDTO);
        Area nuevaArea = areaRepository.save(area);
        return convertToDTO(nuevaArea);
    }

    // Actualizar un área existente
    public AreaDTO actualizar(Long id, AreaDTO areaDTO) {
        Area areaExistente = areaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + id));

        areaExistente.setNombre(areaDTO.getNombre());
        areaExistente.setDescripcion(areaDTO.getDescripcion());

        if (areaDTO.getEmpresas() != null) {
            List<Empresa> empresas = areaDTO.getEmpresas().stream()
                    .map(empresaId -> empresaRepository.findById(empresaId)
                            .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + empresaId)))
                    .collect(Collectors.toList());
            areaExistente.setEmpresas(empresas);
        }

        Area areaActualizada = areaRepository.save(areaExistente);
        return convertToDTO(areaActualizada);
    }

    // Eliminar un área
    public void eliminar(Long id) {
        areaRepository.deleteById(id);
    }

    // Convertir de entidad a DTO
    private AreaDTO convertToDTO(Area area) {
        return new AreaDTO(
                area.getId(),
                area.getNombre(),
                area.getDescripcion(),
                area.getEmpresas() != null
                        ? area.getEmpresas().stream().map(Empresa::getId).collect(Collectors.toList())
                        : null
        );
    }

    // Convertir de DTO a entidad
    private Area convertToEntity(AreaDTO areaDTO) {
        Area area = new Area();
        area.setNombre(areaDTO.getNombre());
        area.setDescripcion(areaDTO.getDescripcion());

        if (areaDTO.getEmpresas() != null) {
            List<Empresa> empresas = areaDTO.getEmpresas().stream()
                    .map(empresaId -> empresaRepository.findById(empresaId)
                            .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + empresaId)))
                    .collect(Collectors.toList());
            area.setEmpresas(empresas);
        }

        return area;
    }
}