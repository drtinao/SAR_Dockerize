package com.eurosoftware.finance.controller;

import com.eurosoftware.finance.DTOs.CustomerFinanceDto;
import com.eurosoftware.finance.DTOs.ProjectFinanceDto;
import com.eurosoftware.finance.entity.Finance;
import com.eurosoftware.finance.service.FinanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/finance")
@Slf4j
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        financeService.save(new Finance(1L, "Popis 1", 123.456, 1L, false));
        financeService.save(new Finance(2L, "Popis 2", 123.456, 1L, true));
        financeService.save(new Finance(3L, "Popis 3", 123.456, 1L, false));
        financeService.save(new Finance(4L, "Popis 4", 123.456, 2L, false));
        financeService.save(new Finance(5L, "Popis 5", 123.456, 2L, false));
        financeService.save(new Finance(6L, "Popis 6", 123.456, 3L, true));
        financeService.save(new Finance(7L, "Popis 7", 123.456, 3L, false));
        financeService.save(new Finance(8L, "Popis 8", 123.456, 3L, false));
        financeService.save(new Finance(9L, "Popis 9", 123.456, 3L, true));
        financeService.save(new Finance(10L, "Popis 10", 123.456, 4L, true));
        financeService.save(new Finance(11L, "Popis 11", 123.456, 4L, true));
        financeService.save(new Finance(12L, "Popis 12", 123.456, 5L, true));
        financeService.save(new Finance(13L, "Popis 13", 123.456, 6L, true));
        financeService.save(new Finance(14L, "Popis 14", 123.456, 7L, false));
        financeService.save(new Finance(15L, "Popis 15", 123.456, 8L, true));
        financeService.save(new Finance(16L, "Popis 16", 123.456, 8L, true));
        financeService.save(new Finance(17L, "Popis 17", 123.456, 3L, true));
        financeService.save(new Finance(18L, "Popis 18", 123.456, 3L, false));
        financeService.save(new Finance(19L, "Popis 19", 123.456, 4L, false));
        financeService.save(new Finance(20L, "Popis 20", 123.456, 4L, false));
        financeService.save(new Finance(21L, "Popis 21", 123.456, 1L, false));
        financeService.save(new Finance(22L, "Popis 22", 123.456, 1L, false));
    }

    @PostMapping("/")
    public Finance saveFinance(@RequestBody Finance user) {
        return financeService.save(user);
    }

    @GetMapping("/by-customer/{id}")
    public List<CustomerFinanceDto> findFinanceByCustomer(@PathVariable("id") Long id) {
        return financeService.getCustomerFinances(id);
    }

    @DeleteMapping("/delete-list")
    public void deleteByProjects(List<Long> ids) {
        financeService.deleteByProjects(ids);
    }

    @GetMapping("/by-project/{id}")
    public List<ProjectFinanceDto> findProjectFinance(@PathVariable("id") Long id) {
        return financeService.getFinanceByProject(id);
    }

    @GetMapping("/all-names")
    public List<Double> findAllNames() {
        return financeService.findAllNames();
    }

}
