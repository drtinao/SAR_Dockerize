package com.eurosoftware.property.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PropertyDto {

    private String name;

    private String location;

    private String type;

    private double price;

}
