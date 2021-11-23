package com.eurosoftware.projects.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFinanceDto {

    private Long id;

    private double amount;

}
