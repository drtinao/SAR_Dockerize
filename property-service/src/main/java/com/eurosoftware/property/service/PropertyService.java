package com.eurosoftware.property.service;

import com.eurosoftware.property.DTOs.PropertyDto;
import com.eurosoftware.property.entity.Property;
import com.eurosoftware.property.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property save(Property user) {
        return propertyRepository.save(user);
    }

    public List<PropertyDto> findPropertyByUser(Long id) {
        var result = new ArrayList<PropertyDto>();

        var list = propertyRepository.findByOwnerId(id);

        for(var item : list) {
            result.add(new PropertyDto(item.getName(), item.getLocation(), item.getType(), item.getPrice()));
        }

        return result;
    }

    public List<String> findAllNames() {
        var list = new ArrayList<String>();
        var properties = propertyRepository.findAll();
        for(var item : properties) list.add(item.getName());
        return list;
    }
}
