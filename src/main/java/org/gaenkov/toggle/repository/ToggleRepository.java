package org.gaenkov.toggle.repository;
import org.gaenkov.toggle.model.Toggle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToggleRepository extends JpaRepository<Toggle, Long> {
}