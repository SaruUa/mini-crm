package com.yehor.minicrm.controller;

import com.yehor.minicrm.entity.Interaction;
import com.yehor.minicrm.service.InteractionService;
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
    public List<Interaction> getAllInteractions() {
        return interactionService.getAllInteractions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interaction> getInteractionById(@PathVariable Long id) {
        return interactionService.getInteractionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public List<Interaction> getInteractionsByClientId(@PathVariable Long clientId) {
        return interactionService.getInteractionsByClientId(clientId);
    }

    @PostMapping
    public ResponseEntity<Interaction> createInteraction(@RequestBody Interaction interaction) {
        try {
            return ResponseEntity.ok(interactionService.createInteraction(interaction));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interaction> updateInteraction(@PathVariable Long id, @RequestBody Interaction interaction) {
        try {
            return ResponseEntity.ok(interactionService.updateInteraction(id, interaction));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInteraction(@PathVariable Long id) {
        interactionService.deleteInteraction(id);
        return ResponseEntity.noContent().build();
    }
}