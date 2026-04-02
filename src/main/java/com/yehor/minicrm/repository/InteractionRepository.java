package com.yehor.minicrm.repository;

import com.yehor.minicrm.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findByClientId(Long clientId);
}