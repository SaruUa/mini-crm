package com.yehor.minicrm.repository;

import com.yehor.minicrm.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}