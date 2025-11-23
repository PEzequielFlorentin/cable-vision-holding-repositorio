package com.holding.holding_management_system.repository;

import com.holding.holding_management_system.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    List<Vendedor> findByEmpresaId(Long empresaId);
}