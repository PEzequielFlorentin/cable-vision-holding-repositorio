package com.holding.holding_management_system.service;

import com.holding.holding_management_system.dto.VendedorDTO;
import com.holding.holding_management_system.model.Empresa;
import com.holding.holding_management_system.model.Vendedor;
import com.holding.holding_management_system.repository.EmpresaRepository;
import com.holding.holding_management_system.repository.VendedorRepository;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Collections;

import org.springframework.stereotype.Service;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final EmpresaRepository empresaRepository;

    // Constructor que inicializa ambos repositorios
    public VendedorService(VendedorRepository vendedorRepository, EmpresaRepository empresaRepository) {
        this.vendedorRepository = vendedorRepository;
        this.empresaRepository = empresaRepository;
    }

    // Método para eliminar un vendedor
    public void eliminarVendedor(Long id) {
        // Verifica si el vendedor existe
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));

        // Elimina el vendedor
        vendedorRepository.delete(vendedor);
    }

    // Método para actualizar un vendedor
    public VendedorDTO actualizarVendedor(Long id, VendedorDTO vendedorDTO) {
        // Verifica si el vendedor existe
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));

        // Actualiza los datos del vendedor
        vendedor.setCodigo(vendedorDTO.getCodigo());
        vendedor.setNombre(vendedorDTO.getNombre());
        vendedor.setDireccion(vendedorDTO.getDireccion());
        vendedor.setFechaCaptacion(vendedorDTO.getFechaCaptacion());

        // Si se proporciona un nuevo ID de empresa, verifica que la empresa exista
        if (vendedorDTO.getEmpresaId() != null) {
            Empresa empresa = empresaRepository.findById(vendedorDTO.getEmpresaId())
                    .orElseThrow(
                            () -> new RuntimeException("Empresa no encontrada con ID: " + vendedorDTO.getEmpresaId()));
            vendedor.setEmpresa(empresa);
        }

        // Si se proporciona un ID de captador, verifica que exista
        if (vendedorDTO.getCaptadorId() != null) {
            Vendedor captador = vendedorRepository.findById(vendedorDTO.getCaptadorId())
                    .orElseThrow(() -> new RuntimeException(
                            "Captador no encontrado con ID: " + vendedorDTO.getCaptadorId()));
            vendedor.setCaptador(captador);
        } else {
            vendedor.setCaptador(null);
        }

        // Guarda los cambios en la base de datos
        Vendedor vendedorActualizado = vendedorRepository.save(vendedor);

        // Convierte la entidad actualizada a DTO y la devuelve
        return convertToDTO(vendedorActualizado);
    }

    public VendedorDTO guardar(VendedorDTO vendedorDTO) {
        // Verifica que el campo codigo no sea null o vacío
        if (vendedorDTO.getCodigo() == null || vendedorDTO.getCodigo().isEmpty()) {
            throw new RuntimeException("El código del vendedor es obligatorio");
        }

        // Verifica que la empresa exista
        Empresa empresa = empresaRepository.findById(vendedorDTO.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + vendedorDTO.getEmpresaId()));

        // Convierte el DTO a entidad
        Vendedor vendedor = new Vendedor();
        vendedor.setCodigo(vendedorDTO.getCodigo());
        vendedor.setNombre(vendedorDTO.getNombre());
        vendedor.setDireccion(vendedorDTO.getDireccion());
        vendedor.setFechaCaptacion(vendedorDTO.getFechaCaptacion());
        vendedor.setEmpresa(empresa);

        // Si se proporciona un ID de captador, verifica que exista
        if (vendedorDTO.getCaptadorId() != null) {
            Vendedor captador = vendedorRepository.findById(vendedorDTO.getCaptadorId())
                    .orElseThrow(() -> new RuntimeException(
                            "Captador no encontrado con ID: " + vendedorDTO.getCaptadorId()));
            vendedor.setCaptador(captador);
        }

        // Guarda el vendedor
        Vendedor nuevoVendedor = vendedorRepository.save(vendedor);

        // Convierte la entidad guardada a DTO
        return convertToDTO(nuevoVendedor);
    }

    // Método para obtener los vendedores captados por un vendedor
    public List<VendedorDTO> obtenerVendedoresCaptados(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));

        List<Vendedor> vendedoresCaptados = vendedor.getVendedoresCaptados();

        // Si no hay vendedores captados, devuelve una lista vacía
        if (vendedoresCaptados.isEmpty()) {
            return Collections.emptyList();
        }

        return vendedoresCaptados.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener todos los vendedores
    public List<VendedorDTO> obtenerTodos() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return vendedores.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Método para obtener un vendedor por ID
    public VendedorDTO obtenerPorId(Long id) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));
        return convertToDTO(vendedor);
    }

    public List<VendedorDTO> obtenerPiramidePorEmpresa(Long empresaId) {
        // Obtiene todos los vendedores de la empresa
        List<Vendedor> vendedores = vendedorRepository.findByEmpresaId(empresaId);

        // Filtra los vendedores que no tienen captador (nivel raíz de la pirámide)
        List<Vendedor> raiz = vendedores.stream()
                .filter(v -> v.getCaptador() == null)
                .collect(Collectors.toList());

        // Construye la pirámide recursivamente
        return raiz.stream()
                .map(this::convertirVendedorConCaptados)
                .collect(Collectors.toList());
    }

    private VendedorDTO convertirVendedorConCaptados(Vendedor vendedor) {
        VendedorDTO vendedorDTO = convertToDTO(vendedor);

        // Convierte los vendedores captados recursivamente
        List<VendedorDTO> captadosDTO = vendedor.getVendedoresCaptados().stream()
                .map(this::convertirVendedorConCaptados)
                .collect(Collectors.toList());

        vendedorDTO.setCaptados(captadosDTO);
        return vendedorDTO;
    }

    private VendedorDTO convertToDTO(Vendedor vendedor) {
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setId(vendedor.getId());
        vendedorDTO.setCodigo(vendedor.getCodigo());
        vendedorDTO.setNombre(vendedor.getNombre());
        vendedorDTO.setDireccion(vendedor.getDireccion());
        vendedorDTO.setFechaCaptacion(vendedor.getFechaCaptacion());
        vendedorDTO.setEmpresaId(vendedor.getEmpresa().getId());
        vendedorDTO.setCaptadorId(vendedor.getCaptador() != null ? vendedor.getCaptador().getId() : null);
        return vendedorDTO;
    }
}