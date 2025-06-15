package org.gaenkov.toggle.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Toggle> toggles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Toggle> getToggles() {
        return toggles;
    }

    public void setToggles(List<Toggle> toggles) {
        this.toggles = toggles;
    }
}
