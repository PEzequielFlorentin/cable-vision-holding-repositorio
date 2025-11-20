package com.holding.holding_management_system.service;

import com.holding.holding_management_system.model.Pais;
import com.holding.holding_management_system.repository.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisService {

    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public List<Pais> obtenerTodos() {
        return paisRepository.findAll();
    }

    public Pais obtenerPorId(Long id) {
        return paisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("País no encontrado con ID: " + id));
    }

    public Pais guardar(Pais pais) {
        return paisRepository.save(pais);
    }

    public Pais actualizar(Long id, Pais pais) {
        Pais existente = obtenerPorId(id);
        existente.setNombre(pais.getNombre());
        existente.setPib(pais.getPib());
        existente.setNumeroHabitantes(pais.getNumeroHabitantes());
        existente.setCapital(pais.getCapital());
        return paisRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!paisRepository.existsById(id)) {
            throw new RuntimeException("País no encontrado con ID: " + id);
        }
        paisRepository.deleteById(id);
    }
}
