package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.PaisDTO;
import com.holding.holding_management_system.model.Pais;
import com.holding.holding_management_system.repository.PaisRepository;
import org.springframework.stereotype.Service;
import com.holding.holding_management_system.dto.EmpresaDTO;
import com.holding.holding_management_system.model.Empresa;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaisService {

    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public List<PaisDTO> obtenerTodos() {
        List<Pais> paises = paisRepository.findAll();
        return paises.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaisDTO obtenerPorId(Long id) {
        Pais pais = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + id));
        return convertToDTO(pais);
    }

    public PaisDTO guardar(PaisDTO paisDTO) {
        Pais pais = convertToEntity(paisDTO);
        Pais nuevoPais = paisRepository.save(pais);
        return convertToDTO(nuevoPais);
    }

    public PaisDTO actualizar(Long id, PaisDTO paisDTO) {
        Pais paisExistente = paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + id));
        paisExistente.setNombre(paisDTO.getNombre());
        paisExistente.setCapital(paisDTO.getCapital());
        paisExistente.setNumeroHabitantes(paisDTO.getNumeroHabitantes());
        paisExistente.setPib(paisDTO.getPib());
        Pais paisActualizado = paisRepository.save(paisExistente);
        return convertToDTO(paisActualizado);
    }

    public void eliminar(Long id) {
        paisRepository.deleteById(id);
    }

    private PaisDTO convertToDTO(Pais pais) {
        return new PaisDTO(
                pais.getId(),
                pais.getNombre(),
                pais.getCapital(),
                pais.getNumeroHabitantes(),
                pais.getPib(),
                null // Aquí puedes mapear las empresas si es necesario
        );
    }

    private Pais convertToEntity(PaisDTO paisDTO) {
        Pais pais = new Pais();
        pais.setNombre(paisDTO.getNombre());
        pais.setCapital(paisDTO.getCapital());
        pais.setNumeroHabitantes(paisDTO.getNumeroHabitantes());
        pais.setPib(paisDTO.getPib());
        return pais;
    }
    // Método para obtener las empresas por país
    public List<EmpresaDTO> obtenerEmpresasPorPais(Long paisId) {
        // Verifica si el país existe
        Pais pais = paisRepository.findById(paisId)
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + paisId));

        // Obtiene la lista de empresas asociadas al país
        List<Empresa> empresas = pais.getEmpresas();

        // Convierte la lista de entidades Empresa a DTOs
        return empresas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para convertir una entidad Empresa a un DTO
    private EmpresaDTO convertToDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setFechaEntrada(empresa.getFechaEntrada());
        empresaDTO.setFacturacionAnual(empresa.getFacturacionAnual());
        empresaDTO.setNumeroVendedores(empresa.getNumeroVendedores());
        return empresaDTO;
    }
}
