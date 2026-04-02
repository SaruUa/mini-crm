package com.yehor.minicrm.controller;

import com.yehor.minicrm.dto.ManagerRequestDto;
import com.yehor.minicrm.dto.ManagerResponseDto;
import com.yehor.minicrm.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<ManagerResponseDto> getAllManagers() {
        return managerService.getAllManagers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponseDto> getManagerById(@PathVariable Long id) {
        return managerService.getManagerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ManagerResponseDto createManager(@Valid @RequestBody ManagerRequestDto managerDto) {
        return managerService.createManager(managerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerResponseDto> updateManager(@PathVariable Long id,
                                                            @Valid @RequestBody ManagerRequestDto managerDto) {
        ManagerResponseDto updatedManager = managerService.updateManager(id, managerDto);
        return ResponseEntity.ok(updatedManager);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}