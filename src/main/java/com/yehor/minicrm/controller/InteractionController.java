package com.yehor.minicrm.controller;

import com.yehor.minicrm.dto.InteractionRequestDto;
import com.yehor.minicrm.dto.InteractionResponseDto;
import com.yehor.minicrm.service.InteractionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping
    public List<InteractionResponseDto> getAllInteractions() {
        return interactionService.getAllInteractions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteractionResponseDto> getInteractionById(@PathVariable Long id) {
        return interactionService.getInteractionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public List<InteractionResponseDto> getInteractionsByClientId(@PathVariable Long clientId) {
        return interactionService.getInteractionsByClientId(clientId);
    }

    @PostMapping
    public InteractionResponseDto createInteraction(@Valid @RequestBody InteractionRequestDto interactionDto) {
        return interactionService.createInteraction(interactionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InteractionResponseDto> updateInteraction(@PathVariable Long id,
                                                                    @Valid @RequestBody InteractionRequestDto interactionDto) {
        InteractionResponseDto updatedInteraction = interactionService.updateInteraction(id, interactionDto);
        return ResponseEntity.ok(updatedInteraction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteraction(@PathVariable Long id) {
        interactionService.deleteInteraction(id);
        return ResponseEntity.noContent().build();
    }
}