package com.finance.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.finance.dashboard.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}