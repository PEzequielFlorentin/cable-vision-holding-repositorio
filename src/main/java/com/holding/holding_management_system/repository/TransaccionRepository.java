package com.holding.holding_management_system.repository;

import com.holding.holding_management_system.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
}