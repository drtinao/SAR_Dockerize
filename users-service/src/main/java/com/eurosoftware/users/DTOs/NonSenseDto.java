package com.eurosoftware.users.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NonSenseDto {

    private List<String> properties = new ArrayList<>();
    private List<String> users = new ArrayList<>();
    private List<String> customers = new ArrayList<>();
    private List<Double> finance = new ArrayList<>();
    private List<String> projects = new ArrayList<>();

}
