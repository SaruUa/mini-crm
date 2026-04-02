package com.yehor.minicrm.service;

import com.yehor.minicrm.entity.Client;
import com.yehor.minicrm.entity.Interaction;
import com.yehor.minicrm.repository.ClientRepository;
import com.yehor.minicrm.repository.InteractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionService {

    private final InteractionRepository interactionRepository;
    private final ClientRepository clientRepository;

    public InteractionService(InteractionRepository interactionRepository, ClientRepository clientRepository) {
        this.interactionRepository = interactionRepository;
        this.clientRepository = clientRepository;
    }

    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    public Optional<Interaction> getInteractionById(Long id) {
        return interactionRepository.findById(id);
    }

    public List<Interaction> getInteractionsByClientId(Long clientId) {
        return interactionRepository.findByClientId(clientId);
    }

    public Interaction createInteraction(Interaction interaction) {
        if (interaction.getClient() != null && interaction.getClient().getId() != null) {
            Client client = clientRepository.findById(interaction.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client not found with id: " + interaction.getClient().getId()));
            interaction.setClient(client);
        } else {
            throw new RuntimeException("Client is required for interaction");
        }

        return interactionRepository.save(interaction);
    }

    public Interaction updateInteraction(Long id, Interaction updatedInteraction) {
        Interaction existingInteraction = interactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interaction not found with id: " + id));

        existingInteraction.setType(updatedInteraction.getType());
        existingInteraction.setDescription(updatedInteraction.getDescription());
        existingInteraction.setInteractionDate(updatedInteraction.getInteractionDate());

        if (updatedInteraction.getClient() != null && updatedInteraction.getClient().getId() != null) {
            Client client = clientRepository.findById(updatedInteraction.getClient().getId())
                    .orElseThrow(() -> new RuntimeException("Client not found with id: " + updatedInteraction.getClient().getId()));
            existingInteraction.setClient(client);
        }

        return interactionRepository.save(existingInteraction);
    }

    public void deleteInteraction(Long id) {
        interactionRepository.deleteById(id);
    }
}