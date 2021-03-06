package com.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Subscription {
    private int id;
    private String name;
    private List<Tariff> tariffs;
    private boolean isActive;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Subscription() {
    }

    public Subscription(String name) {
        this.name = name;
    }


    public Subscription(String name,boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public Subscription(int id , String name,boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public Subscription(int id, String name,boolean isActive, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.name = name;

        this.isActive = isActive;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public Timestamp convertToTimestamp(LocalDateTime date) {
        if(date == null) date = LocalDateTime.now();
        return Timestamp.valueOf(date);
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
