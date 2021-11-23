package com.eurosoftware.customers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class CustomerOverviewDto {

    private Long id;

    private String name;

}
