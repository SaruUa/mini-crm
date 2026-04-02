package com.yehor.minicrm.service;

import com.yehor.minicrm.dto.ClientRequestDto;
import com.yehor.minicrm.dto.ClientResponseDto;
import com.yehor.minicrm.entity.Client;
import com.yehor.minicrm.entity.Manager;
import com.yehor.minicrm.repository.ClientRepository;
import com.yehor.minicrm.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    public ClientService(ClientRepository clientRepository, ManagerRepository managerRepository) {
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
    }

    public List<ClientResponseDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public Optional<ClientResponseDto> getClientById(Long id) {
        return clientRepository.findById(id).map(this::mapToResponseDto);
    }

    public ClientResponseDto createClient(ClientRequestDto dto) {
        Client client = new Client();
        applyDtoToClient(client, dto);
        return mapToResponseDto(clientRepository.save(client));
    }

    public ClientResponseDto updateClient(Long id, ClientRequestDto dto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        applyDtoToClient(existingClient, dto);
        return mapToResponseDto(clientRepository.save(existingClient));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    private void applyDtoToClient(Client client, ClientRequestDto dto) {
        client.setFullName(dto.getFullName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setCompany(dto.getCompany());
        client.setStatus(dto.getStatus());

        if (dto.getManagerId() != null) {
            Manager manager = managerRepository.findById(dto.getManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found with id: " + dto.getManagerId()));
            client.setManager(manager);
        } else {
            client.setManager(null);
        }
    }

    private ClientResponseDto mapToResponseDto(Client client) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(client.getId());
        dto.setFullName(client.getFullName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setCompany(client.getCompany());
        dto.setStatus(client.getStatus());

        if (client.getManager() != null) {
            dto.setManagerId(client.getManager().getId());
            dto.setManagerName(client.getManager().getFirstName() + " " + client.getManager().getLastName());
        }

        return dto;
    }
}