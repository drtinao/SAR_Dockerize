package com.eurosoftware.users.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOverviewDto {

    private Long id;

    private String name;

    private List<PropertyDto> properties = new ArrayList<>();

}
