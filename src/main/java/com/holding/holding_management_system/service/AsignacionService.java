package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.AsignacionDTO;
import com.holding.holding_management_system.model.Area;
import com.holding.holding_management_system.model.Asesor;
import com.holding.holding_management_system.model.Asignacion;
import com.holding.holding_management_system.model.Empresa;
import com.holding.holding_management_system.repository.AreaRepository;
import com.holding.holding_management_system.repository.AsesorRepository;
import com.holding.holding_management_system.repository.AsignacionRepository;
import com.holding.holding_management_system.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class AsignacionService {

    private final AsignacionRepository asignacionRepository;
    private final AsesorRepository asesorRepository;
    private final EmpresaRepository empresaRepository;
    private final AreaRepository areaRepository;

    public AsignacionService(AsignacionRepository asignacionRepository, AsesorRepository asesorRepository,
                             EmpresaRepository empresaRepository, AreaRepository areaRepository) {
        this.asignacionRepository = asignacionRepository;
        this.asesorRepository = asesorRepository;
        this.empresaRepository = empresaRepository;
        this.areaRepository = areaRepository;
    }

    public void asignarAsesor(Long asesorId, AsignacionDTO asignacionDTO) {
        // Verifica si el asesor existe
        Asesor asesor = asesorRepository.findById(asesorId)
                .orElseThrow(() -> new RuntimeException("Asesor no encontrado con ID: " + asesorId));

        // Verifica si la empresa existe
        Empresa empresa = empresaRepository.findById(asignacionDTO.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + asignacionDTO.getEmpresaId()));

        // Verifica si el área existe
        Area area = areaRepository.findById(asignacionDTO.getAreaId())
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + asignacionDTO.getAreaId()));

        // Crea la asignación
        Asignacion asignacion = new Asignacion();
        asignacion.setAsesor(asesor);
        asignacion.setEmpresa(empresa);
        asignacion.setArea(area);
        asignacion.setFechaInicio(asignacionDTO.getFechaInicio());

        // Guarda la asignación
        asignacionRepository.save(asignacion);
    }
}
