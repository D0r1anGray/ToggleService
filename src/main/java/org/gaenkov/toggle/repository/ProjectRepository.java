package org.gaenkov.toggle.repository;


import org.gaenkov.toggle.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String name);
}