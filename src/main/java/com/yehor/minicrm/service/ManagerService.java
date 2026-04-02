package com.yehor.minicrm.service;

import com.yehor.minicrm.dto.ManagerRequestDto;
import com.yehor.minicrm.dto.ManagerResponseDto;
import com.yehor.minicrm.entity.Manager;
import com.yehor.minicrm.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<ManagerResponseDto> getAllManagers() {
        return managerRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public Optional<ManagerResponseDto> getManagerById(Long id) {
        return managerRepository.findById(id).map(this::mapToResponseDto);
    }

    public ManagerResponseDto createManager(ManagerRequestDto dto) {
        Manager manager = new Manager();
        manager.setFirstName(dto.getFirstName());
        manager.setLastName(dto.getLastName());
        manager.setEmail(dto.getEmail());

        return mapToResponseDto(managerRepository.save(manager));
    }

    public ManagerResponseDto updateManager(Long id, ManagerRequestDto dto) {
        Manager existingManager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id: " + id));

        existingManager.setFirstName(dto.getFirstName());
        existingManager.setLastName(dto.getLastName());
        existingManager.setEmail(dto.getEmail());

        return mapToResponseDto(managerRepository.save(existingManager));
    }

    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }

    private ManagerResponseDto mapToResponseDto(Manager manager) {
        ManagerResponseDto dto = new ManagerResponseDto();
        dto.setId(manager.getId());
        dto.setFirstName(manager.getFirstName());
        dto.setLastName(manager.getLastName());
        dto.setEmail(manager.getEmail());
        return dto;
    }
}