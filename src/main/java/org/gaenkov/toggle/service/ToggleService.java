package org.gaenkov.toggle.service;

import org.gaenkov.toggle.model.Toggle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ToggleService {
    private static final Logger logger = LoggerFactory.getLogger(ToggleService.class);

    public List<Toggle> getToggles(String projectName, String settingsType) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Map<String, Object> config = yaml.load(new FileInputStream("src/main/resources/config.yaml"));

        if (config == null) {
            logger.error("Файл конфигурации не найден.");
            throw new FileNotFoundException("Файл конфигурации не найден.");
        }

        Map<String, Object> project = (Map<String, Object>) config.get("project");
        if (project == null || !project.get("name").equals(projectName)) {
            logger.error("Проект не найден в файле конфигурации.");
            throw new FileNotFoundException("Проект не найден в файле конфигурации.");
        }

        List<Map<String, Object>> toggles = (List<Map<String, Object>>) project.get("toggles");
        if (toggles == null) {
            logger.info("Настройки для проекта {} отсутствуют.", projectName);
            return List.of();
        }

        if (settingsType == null || settingsType.isEmpty()) {
            logger.info("Возвращаем все тоглы для проекта {}.", projectName);
            return toggles.stream().map(this::mapToToggle).collect(Collectors.toList());
        } else {
            List<Toggle> filteredToggles = toggles.stream()
                    .filter(t -> t.get("title").equals(settingsType))
                    .map(this::mapToToggle)
                    .collect(Collectors.toList());

            if (filteredToggles.isEmpty()) {
                logger.error("Запрашиваемые настройки не найдены в файле конфигурации.");
                throw new FileNotFoundException("Запрашиваемые настройки не найдены в файле конфигурации.");
            }

            logger.info("Найден тогл {} для проекта {}.", settingsType, projectName);
            return filteredToggles;
        }
    }

    private Toggle mapToToggle(Map<String, Object> toggleMap) {
        Toggle toggle = new Toggle();
        toggle.setDescription((String) toggleMap.get("description"));
        toggle.setTitle((String) toggleMap.get("title"));
        toggle.setEnabled((boolean) toggleMap.get("enabled"));
        return toggle;
    }
}