package com.yehor.minicrm.dto;

import com.yehor.minicrm.entity.InteractionType;

import java.time.LocalDateTime;

public class InteractionResponseDto {

    private Long id;
    private InteractionType type;
    private String description;
    private LocalDateTime interactionDate;
    private Long clientId;
    private String clientName;

    public InteractionResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}