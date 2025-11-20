package com.holding.holding_management_system.service;

import com.holding.holding_management_system.model.Asesor;
import com.holding.holding_management_system.repository.AsesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsesorService {

    private final AsesorRepository asesorRepository;

    public AsesorService(AsesorRepository asesorRepository) {
        this.asesorRepository = asesorRepository;
    }

    public List<Asesor> obtenerTodos() {
        return asesorRepository.findAll();
    }

    public Asesor obtenerPorId(Long id) {
        return asesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asesor no encontrado con ID: " + id));
    }

    public Asesor guardar(Asesor asesor) {
        return asesorRepository.save(asesor);
    }

    public Asesor actualizar(Long id, Asesor asesor) {
        Asesor existente = obtenerPorId(id);
        existente.setNombre(asesor.getNombre());
        existente.setDireccion(asesor.getDireccion());
        existente.setTitulacion(asesor.getTitulacion());
        return asesorRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!asesorRepository.existsById(id)) {
            throw new RuntimeException("Asesor no encontrado con ID: " + id);
        }
        asesorRepository.deleteById(id);
    }
}
