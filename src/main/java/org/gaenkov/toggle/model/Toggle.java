package org.gaenkov.toggle.model;

import jakarta.persistence.*;

@Entity
public class Toggle {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String title;
    private boolean enabled;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}