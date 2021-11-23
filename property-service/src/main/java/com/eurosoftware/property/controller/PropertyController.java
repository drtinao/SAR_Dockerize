package com.eurosoftware.property.controller;

import com.eurosoftware.property.DTOs.PropertyDto;
import com.eurosoftware.property.entity.Property;
import com.eurosoftware.property.service.PropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
@Slf4j
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        propertyService.save(new Property(1L, "Majetek 1", "Umístění 1", "Typ 1", 123.456, null));
        propertyService.save(new Property(2L, "Majetek 2", "Umístění 2", "Typ 2", 123.456, 2L));
        propertyService.save(new Property(3L, "Majetek 3", "Umístění 3", "Typ 3", 123.456, 2L));
        propertyService.save(new Property(4L, "Majetek 4", "Umístění 4", "Typ 4", 123.456, null));
        propertyService.save(new Property(5L, "Majetek 5", "Umístění 5", "Typ 5", 123.456, 1L));
        propertyService.save(new Property(6L, "Majetek 6", "Umístění 6", "Typ 6", 123.456, null));
        propertyService.save(new Property(7L, "Majetek 7", "Umístění 7", "Typ 7", 123.456, 3L));
        propertyService.save(new Property(8L, "Majetek 8", "Umístění 8", "Typ 8", 123.456, 4L));
        propertyService.save(new Property(9L, "Majetek 9", "Umístění 9", "Typ 9", 123.456, 5L));
        propertyService.save(new Property(10L, "Majetek 10", "Umístění 10", "Typ 10", 123.456, 5L));
        propertyService.save(new Property(11L, "Majetek 11", "Umístění 11", "Typ 11", 123.456, null));
        propertyService.save(new Property(12L, "Majetek 12", "Umístění 12", "Typ 12", 123.456, 2L));
    }

    @PostMapping("/")
    public Property saveProperty(@RequestBody Property user) {
        return propertyService.save(user);
    }

    @GetMapping("/{id}")
    public List<PropertyDto> findPropertyByUser(@PathVariable("id") Long id) {
        return propertyService.findPropertyByUser(id);
    }

    @GetMapping("/all-names")
    public List<String> findAllNames() {
        return propertyService.findAllNames();
    }
}
