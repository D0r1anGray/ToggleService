package org.gaenkov.toggle.controller;

import org.gaenkov.toggle.model.Toggle;
import org.gaenkov.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ToggleController {
    @Autowired
    private ToggleService toggleService;

    @GetMapping("/toggle/list")
    public ResponseEntity<?> getToggleList(@RequestParam String projectName, @RequestParam(required = false) String settingsType) {
        try {
            List<Toggle> toggles = toggleService.getToggles(projectName, settingsType);
            return ResponseEntity.ok(toggles);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}