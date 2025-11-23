package com.holding.holding_management_system.repository;

import com.holding.holding_management_system.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    java.util.Optional<Pais> findByNombre(String nombre);
}