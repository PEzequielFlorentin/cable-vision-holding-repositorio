package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.AreaDTO;
import com.holding.holding_management_system.dto.EmpresaDTO;
import com.holding.holding_management_system.dto.VendedorDTO;
import com.holding.holding_management_system.model.Area;
import com.holding.holding_management_system.model.Empresa;
import com.holding.holding_management_system.model.Pais;
import com.holding.holding_management_system.model.Vendedor;
import com.holding.holding_management_system.repository.AreaRepository;
import com.holding.holding_management_system.repository.EmpresaRepository;
import com.holding.holding_management_system.repository.PaisRepository;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final PaisRepository paisRepository;
    private final AreaRepository areaRepository; // Inyección del repositorio de áreas

    public EmpresaService(EmpresaRepository empresaRepository, PaisRepository paisRepository, AreaRepository areaRepository) {
        this.empresaRepository = empresaRepository;
        this.paisRepository = paisRepository;
        this.areaRepository = areaRepository; // Inicialización del repositorio de áreas
    }

    public void asignarAreas(Long empresaId, List<Long> areaIds) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + empresaId));
    
        List<Area> areas = areaRepository.findAllById(areaIds);
    
        empresa.getAreas().addAll(areas);
        empresaRepository.save(empresa);
    }

    // Método para obtener las áreas de una empresa
    public List<AreaDTO> obtenerAreasDeEmpresa(Long id) {
        // Verifica si la empresa existe
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));

        // Obtiene la lista de áreas asociadas a la empresa
        List<Area> areas = empresa.getAreas();

        // Convierte la lista de entidades Area a DTOs
        return areas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para convertir una entidad Area a un DTO
    private AreaDTO convertToDTO(Area area) {
        AreaDTO areaDTO = new AreaDTO();
        areaDTO.setId(area.getId());
        areaDTO.setNombre(area.getNombre());
        areaDTO.setDescripcion(area.getDescripcion());
        return areaDTO;
    }

    // Método para obtener los vendedores de una empresa
    public List<VendedorDTO> obtenerVendedoresDeEmpresa(Long id) {
        // Verifica si la empresa existe
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));

        // Obtiene la lista de vendedores asociados a la empresa
        List<Vendedor> vendedores = empresa.getVendedores();

        // Convierte la lista de entidades Vendedor a DTOs
        return vendedores.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para convertir una entidad Vendedor a un DTO
    private VendedorDTO convertToDTO(Vendedor vendedor) {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setId(vendedor.getId());
        vendedorDTO.setCodigo(vendedor.getCodigo());
        vendedorDTO.setNombre(vendedor.getNombre());
        vendedorDTO.setDireccion(vendedor.getDireccion());
        vendedorDTO.setFechaCaptacion(vendedor.getFechaCaptacion());
        vendedorDTO.setEmpresaId(vendedor.getEmpresa().getId());
        return vendedorDTO;
    }

    // Método para eliminar una empresa
    public void eliminarEmpresa(Long id) {
        // Verifica si la empresa existe
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));

        // Elimina la empresa
        empresaRepository.delete(empresa);
    }
    // Método para actualizar una empresa
    public EmpresaDTO actualizarEmpresa(Long id, EmpresaDTO empresaDTO) {
        // Verifica si la empresa existe
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));

        // Actualiza los datos de la empresa
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setFechaEntrada(empresaDTO.getFechaEntrada());
        empresa.setFacturacionAnual(empresaDTO.getFacturacionAnual());
        empresa.setNumeroVendedores(empresaDTO.getNumeroVendedores());

        // Si se proporciona un nuevo ID de país, verifica que el país exista
        if (empresaDTO.getSedePaisId() != null) {
            Pais pais = paisRepository.findById(empresaDTO.getSedePaisId())
                    .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + empresaDTO.getSedePaisId()));
            empresa.setSede(pais); // Cambiado de setSedePais a setSede
        }

        // Guarda los cambios en la base de datos
        Empresa empresaActualizada = empresaRepository.save(empresa);

        // Convierte la entidad actualizada a DTO y la devuelve
        return convertToDTO(empresaActualizada);
    }

    public List<EmpresaDTO> obtenerTodas() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public EmpresaDTO obtenerPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
        return convertToDTO(empresa);
    }  
    public EmpresaDTO guardar(EmpresaDTO empresaDTO) {
        Empresa empresa = convertToEntity(empresaDTO);
        Empresa nuevaEmpresa = empresaRepository.save(empresa);
        return convertToDTO(nuevaEmpresa);
    }

    private Empresa convertToEntity(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setFacturacionAnual(empresaDTO.getFacturacionAnual());
        empresa.setNumeroVendedores(empresaDTO.getNumeroVendedores());
        empresa.setFechaEntrada(empresaDTO.getFechaEntrada());

        if (empresaDTO.getPais() != null) {
            Pais pais = paisRepository.findById(empresaDTO.getPais())
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + empresaDTO.getPais()));
            empresa.setSede(pais);
        }
        // Aquí puedes mapear áreas y vendedores si están relacionados
        return empresa;
    }

    private EmpresaDTO convertToDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setFacturacionAnual(empresa.getFacturacionAnual());
        empresaDTO.setNumeroVendedores(empresa.getNumeroVendedores());
        empresaDTO.setFechaEntrada(empresa.getFechaEntrada());
        empresaDTO.setPais(empresa.getSede() != null ? empresa.getSede().getId() : null);
        // Aquí puedes mapear áreas y vendedores si están relacionados
        return empresaDTO;
    }
}