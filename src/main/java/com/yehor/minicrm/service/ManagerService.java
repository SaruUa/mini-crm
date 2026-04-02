package com.yehor.minicrm.service;

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

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Optional<Manager> getManagerById(Long id) {
        return managerRepository.findById(id);
    }

    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    public Manager updateManager(Long id, Manager updatedManager) {
        Manager existingManager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found with id: " + id));

        existingManager.setFirstName(updatedManager.getFirstName());
        existingManager.setLastName(updatedManager.getLastName());
        existingManager.setEmail(updatedManager.getEmail());

        return managerRepository.save(existingManager);
    }

    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }
}