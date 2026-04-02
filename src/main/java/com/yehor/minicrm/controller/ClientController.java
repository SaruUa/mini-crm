package com.yehor.minicrm.controller;

import com.yehor.minicrm.dto.ClientRequestDto;
import com.yehor.minicrm.dto.ClientResponseDto;
import com.yehor.minicrm.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yehor.minicrm.entity.ClientStatus;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientResponseDto> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClientResponseDto createClient(@Valid @RequestBody ClientRequestDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequestDto clientDto) {
        ClientResponseDto updatedClient = clientService.updateClient(id, clientDto);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/status/{status}")
    public List<ClientResponseDto> getClientsByStatus(@PathVariable ClientStatus status) {
        return clientService.getClientsByStatus(status);
    }

    @GetMapping("/manager/{managerId}")
    public List<ClientResponseDto> getClientsByManagerId(@PathVariable Long managerId) {
        return clientService.getClientsByManagerId(managerId);
    }

    @GetMapping("/search")
    public List<ClientResponseDto> searchClientsByName(@RequestParam String name) {
        return clientService.searchClientsByName(name);
    }
}