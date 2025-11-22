package com.holding.holding_management_system.repository;

import com.holding.holding_management_system.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AreaRepository extends JpaRepository<Area, Long> {
}
