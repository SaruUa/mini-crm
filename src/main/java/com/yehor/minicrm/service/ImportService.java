package com.yehor.minicrm.service;

import com.yehor.minicrm.dto.ExternalUserDto;
import com.yehor.minicrm.entity.Client;
import com.yehor.minicrm.entity.ClientStatus;
import com.yehor.minicrm.entity.Manager;
import com.yehor.minicrm.repository.ClientRepository;
import com.yehor.minicrm.repository.ManagerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImportService {

    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;

    public ImportService(ClientRepository clientRepository, ManagerRepository managerRepository) {
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
    }

    public String importClientsFromApi(Long managerId) {
        Manager manager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));

        RestTemplate restTemplate = new RestTemplate();

        ExternalUserDto[] users = restTemplate.getForObject(
                "https://jsonplaceholder.typicode.com/users",
                ExternalUserDto[].class
        );

        if (users == null || users.length == 0) {
            return "No users found from external API";
        }

        int importedCount = 0;

        for (ExternalUserDto user : users) {
            Client client = new Client();
            client.setFullName(user.getName());
            client.setEmail(user.getEmail());
            client.setPhone(user.getPhone());
            client.setCompany(user.getCompany() != null ? user.getCompany().getName() : "Unknown Company");
            client.setStatus(ClientStatus.NEW);
            client.setManager(manager);

            clientRepository.save(client);
            importedCount++;
        }

        return "Imported clients: " + importedCount;
    }
}