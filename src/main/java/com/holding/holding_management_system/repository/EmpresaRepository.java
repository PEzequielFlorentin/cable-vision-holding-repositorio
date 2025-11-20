package com.holding.holding_management_system.repository;

import com.holding.holding_management_system.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}