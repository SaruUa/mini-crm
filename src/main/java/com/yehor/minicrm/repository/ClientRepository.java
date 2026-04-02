package com.yehor.minicrm.repository;

import com.yehor.minicrm.entity.Client;
import com.yehor.minicrm.entity.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByStatus(ClientStatus status);
    List<Client> findByManagerId(Long managerId);
    List<Client> findByFullNameContainingIgnoreCase(String fullName);
}