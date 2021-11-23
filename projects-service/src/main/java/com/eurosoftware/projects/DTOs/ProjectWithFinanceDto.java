package com.eurosoftware.projects.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWithFinanceDto {

    private Long id;

    private String projectName;

    private List<ProjectFinanceDto> finance;

}