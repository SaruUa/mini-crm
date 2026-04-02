package com.yehor.minicrm.dto;

import com.yehor.minicrm.entity.InteractionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class InteractionRequestDto {

    @NotNull
    private InteractionType type;

    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime interactionDate;

    @NotNull
    private Long clientId;

    public InteractionRequestDto() {
    }

    public InteractionType getType() {
        return type;
    }

    public void setType(InteractionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(LocalDateTime interactionDate) {
        this.interactionDate = interactionDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}