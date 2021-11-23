package com.eurosoftware.finance.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFinanceDto {

    private Long id;

    private Long projectId;

    private double amount;

}
