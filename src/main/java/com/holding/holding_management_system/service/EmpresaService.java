package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.EmpresaDTO;
import com.holding.holding_management_system.model.Empresa;
import com.holding.holding_management_system.model.Pais;
import com.holding.holding_management_system.repository.EmpresaRepository;
import com.holding.holding_management_system.repository.PaisRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final PaisRepository paisRepository;

    public EmpresaService(EmpresaRepository empresaRepository, PaisRepository paisRepository) {
        this.empresaRepository = empresaRepository;
        this.paisRepository = paisRepository;
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

        // Buscar el país por nombre
        if (empresaDTO.getPais() != null) {
            Pais pais = paisRepository.findByNombre(empresaDTO.getPais())
                .orElseThrow(() -> new RuntimeException("País no encontrado: " + empresaDTO.getPais()));
            empresa.setSede(pais);
        }

        // Aquí puedes mapear áreas y vendedores si están relacionados
        return empresa;
    }

    private EmpresaDTO convertToDTO(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setFacturacionAnual(empresa.getFacturacionAnual());
        empresaDTO.setNumeroVendedores(empresa.getNumeroVendedores());
        empresaDTO.setFechaEntrada(empresa.getFechaEntrada());
        empresaDTO.setPais(empresa.getSede() != null ? empresa.getSede().getNombre() : null);
        // Aquí puedes mapear áreas y vendedores si están relacionados
        return empresaDTO;
    }
}