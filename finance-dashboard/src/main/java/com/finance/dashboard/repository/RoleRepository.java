package com.finance.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finance.dashboard.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}