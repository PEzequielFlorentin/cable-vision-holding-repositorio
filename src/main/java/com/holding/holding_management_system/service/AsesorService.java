package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.AsesorDTO;
import com.holding.holding_management_system.dto.AsignacionDTO;
import com.holding.holding_management_system.model.Asesor;
import com.holding.holding_management_system.model.Area;
import com.holding.holding_management_system.model.Asignacion;
import com.holding.holding_management_system.model.Empresa;
import com.holding.holding_management_system.repository.AsesorRepository;
import com.holding.holding_management_system.repository.AreaRepository;
import com.holding.holding_management_system.repository.AsignacionRepository;
import com.holding.holding_management_system.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsesorService {

    private final AsesorRepository asesorRepository;
    private final AreaRepository areaRepository;
    private final EmpresaRepository empresaRepository;
    private final AsignacionRepository asignacionRepository; // Inyección del repositorio AsignacionRepository
    
    public AsesorService(AsesorRepository asesorRepository, AreaRepository areaRepository,
                        EmpresaRepository empresaRepository, AsignacionRepository asignacionRepository) {
        this.asesorRepository = asesorRepository;
        this.areaRepository = areaRepository;
        this.empresaRepository = empresaRepository;
        this.asignacionRepository = asignacionRepository; // Inicialización del repositorio
    }

    // Obtener todos los asesores
    public List<AsesorDTO> obtenerTodos() {
        List<Asesor> asesores = asesorRepository.findAll();
        return asesores.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Obtener un asesor por ID
    public AsesorDTO obtenerPorId(Long id) {
        Asesor asesor = asesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asesor no encontrado con ID: " + id));
        return convertToDTO(asesor);
    }

    // Crear un nuevo asesor
    public AsesorDTO guardar(AsesorDTO asesorDTO) {
        if (asesorDTO.getCodigo() == null || asesorDTO.getCodigo().isEmpty()) {
            throw new RuntimeException("El código del asesor es obligatorio");
        }
    
        Asesor asesor = convertToEntity(asesorDTO);
        Asesor nuevoAsesor = asesorRepository.save(asesor);
        return convertToDTO(nuevoAsesor);
    }

    // Actualizar un asesor existente
    public AsesorDTO actualizar(Long id, AsesorDTO asesorDTO) {
        Asesor asesorExistente = asesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asesor no encontrado con ID: " + id));

        asesorExistente.setCodigo(asesorDTO.getCodigo());
        asesorExistente.setNombre(asesorDTO.getNombre());
        asesorExistente.setDireccion(asesorDTO.getDireccion());
        asesorExistente.setTitulacion(asesorDTO.getTitulacion());

        if (asesorDTO.getAreas() != null) {
            List<Area> areas = asesorDTO.getAreas().stream()
                    .map(areaId -> areaRepository.findById(areaId)
                            .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + areaId)))
                    .collect(Collectors.toList());
            asesorExistente.setAreas(areas);
        }

        if (asesorDTO.getEmpresas() != null) {
            List<Empresa> empresas = asesorDTO.getEmpresas().stream()
                    .map(empresaId -> empresaRepository.findById(empresaId)
                            .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + empresaId)))
                    .collect(Collectors.toList());
            asesorExistente.setEmpresas(empresas);
        }

        Asesor asesorActualizado = asesorRepository.save(asesorExistente);
        return convertToDTO(asesorActualizado);
    }

    // Eliminar un asesor
    public void eliminar(Long id) {
        asesorRepository.deleteById(id);
    }

    // Convertir de entidad a DTO
    private AsesorDTO convertToDTO(Asesor asesor) {
        return new AsesorDTO(
                asesor.getId(),
                asesor.getCodigo(),
                asesor.getNombre(),
                asesor.getDireccion(),
                asesor.getTitulacion(),
                asesor.getAreas() != null ? asesor.getAreas().stream().map(Area::getId).collect(Collectors.toList()) : null,
                asesor.getEmpresas() != null ? asesor.getEmpresas().stream().map(Empresa::getId).collect(Collectors.toList()) : null
        );
    }

    // Convertir de DTO a entidad
    private Asesor convertToEntity(AsesorDTO asesorDTO) {
        Asesor asesor = new Asesor();
        asesor.setCodigo(asesorDTO.getCodigo());
        asesor.setNombre(asesorDTO.getNombre());
        asesor.setDireccion(asesorDTO.getDireccion());
        asesor.setTitulacion(asesorDTO.getTitulacion());

        if (asesorDTO.getAreas() != null) {
            List<Area> areas = asesorDTO.getAreas().stream()
                    .map(areaId -> areaRepository.findById(areaId)
                            .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + areaId)))
                    .collect(Collectors.toList());
            asesor.setAreas(areas);
        }

        if (asesorDTO.getEmpresas() != null) {
            List<Empresa> empresas = asesorDTO.getEmpresas().stream()
                    .map(empresaId -> empresaRepository.findById(empresaId)
                            .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + empresaId)))
                    .collect(Collectors.toList());
            asesor.setEmpresas(empresas);
        }

        return asesor;
    }
    // Método para asignar un asesor a una empresa y un área
    public void asignarAEmpresaYArea(Long asesorId, AsignacionDTO asignacionDTO) {
        // Verifica si el asesor existe
        Asesor asesor = asesorRepository.findById(asesorId)
                .orElseThrow(() -> new RuntimeException("Asesor no encontrado con ID: " + asesorId));

        // Verifica si la empresa existe
        Empresa empresa = empresaRepository.findById(asignacionDTO.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + asignacionDTO.getEmpresaId()));

        // Verifica si el área existe
        Area area = areaRepository.findById(asignacionDTO.getAreaId())
                .orElseThrow(() -> new RuntimeException("Área no encontrada con ID: " + asignacionDTO.getAreaId()));

        // Realiza la asignación
        Asignacion asignacion = new Asignacion();
        asignacion.setAsesor(asesor);
        asignacion.setEmpresa(empresa);
        asignacion.setArea(area);
        asignacion.setFechaInicio(asignacionDTO.getFechaInicio());

        // Guarda la asignación
        asignacionRepository.save(asignacion);
    }
}