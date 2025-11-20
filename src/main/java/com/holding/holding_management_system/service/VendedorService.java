package com.holding.holding_management_system.service;

import com.holding.holding_management_system.model.Vendedor;
import com.holding.holding_management_system.repository.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public List<Vendedor> obtenerTodos() {
        return vendedorRepository.findAll();
    }

    public Vendedor obtenerPorId(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado con ID: " + id));
    }

    public Vendedor guardar(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor actualizar(Long id, Vendedor vendedor) {
        Vendedor existente = obtenerPorId(id);
        existente.setNombre(vendedor.getNombre());
        existente.setDireccion(vendedor.getDireccion());
        existente.setEmpresa(vendedor.getEmpresa());
        return vendedorRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!vendedorRepository.existsById(id)) {
            throw new RuntimeException("Vendedor no encontrado con ID: " + id);
        }
        vendedorRepository.deleteById(id);
    }
}
