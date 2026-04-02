package com.yehor.minicrm.service;

import com.yehor.minicrm.dto.InteractionRequestDto;
import com.yehor.minicrm.dto.InteractionResponseDto;
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

    public List<InteractionResponseDto> getAllInteractions() {
        return interactionRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public Optional<InteractionResponseDto> getInteractionById(Long id) {
        return interactionRepository.findById(id).map(this::mapToResponseDto);
    }

    public List<InteractionResponseDto> getInteractionsByClientId(Long clientId) {
        return interactionRepository.findByClientId(clientId).stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public InteractionResponseDto createInteraction(InteractionRequestDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + dto.getClientId()));

        Interaction interaction = new Interaction();
        interaction.setType(dto.getType());
        interaction.setDescription(dto.getDescription());
        interaction.setInteractionDate(dto.getInteractionDate());
        interaction.setClient(client);

        return mapToResponseDto(interactionRepository.save(interaction));
    }

    public InteractionResponseDto updateInteraction(Long id, InteractionRequestDto dto) {
        Interaction existingInteraction = interactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interaction not found with id: " + id));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + dto.getClientId()));

        existingInteraction.setType(dto.getType());
        existingInteraction.setDescription(dto.getDescription());
        existingInteraction.setInteractionDate(dto.getInteractionDate());
        existingInteraction.setClient(client);

        return mapToResponseDto(interactionRepository.save(existingInteraction));
    }

    public void deleteInteraction(Long id) {
        interactionRepository.deleteById(id);
    }

    private InteractionResponseDto mapToResponseDto(Interaction interaction) {
        InteractionResponseDto dto = new InteractionResponseDto();
        dto.setId(interaction.getId());
        dto.setType(interaction.getType());
        dto.setDescription(interaction.getDescription());
        dto.setInteractionDate(interaction.getInteractionDate());

        if (interaction.getClient() != null) {
            dto.setClientId(interaction.getClient().getId());
            dto.setClientName(interaction.getClient().getFullName());
        }

        return dto;
    }
}