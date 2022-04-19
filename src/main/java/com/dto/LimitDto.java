package com.dto;

import java.time.LocalDateTime;

public class LimitDto {
    private int id;
    private String name;
    private int amount;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;

    public LimitDto() {
    }

    public LimitDto(int id, String name, int amount, boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
