package com.holding.holding_management_system.repository;

import com.holding.holding_management_system.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
