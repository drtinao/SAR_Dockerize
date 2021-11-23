package com.eurosoftware.customers.controller;

import com.eurosoftware.customers.DTOs.CustomerOverviewDto;
import com.eurosoftware.customers.entity.Customer;
import com.eurosoftware.customers.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        customerService.save(new Customer(1L, "Zákazník 1", "123456789", "zakaznik1@sar.fav","Sídlo 1", "123456789"));
        customerService.save(new Customer(2L, "Zákazník 2", "123456789", "zakaznik2@sar.fav", "Sídlo 2", "123456789"));
        customerService.save(new Customer(3L, "Zákazník 3", "123456789", "zakaznik3@sar.fav", "Sídlo 3", "123456789"));
        customerService.save(new Customer(4L, "Zákazník 4", "123456789", "zakaznik4@sar.fav", "Sídlo 4", "123456789"));
        customerService.save(new Customer(5L, "Zákazník 5", "123456789", "zakaznik5@sar.fav", "Sídlo 5", "123456789"));
        customerService.save(new Customer(6L, "Zákazník 6", "123456789", "zakaznik6@sar.fav", "Sídlo 6", "123456789"));
    }

    @PostMapping("/")
    public Customer saveUser(@RequestBody Customer user) {
        return customerService.save(user);
    }

    @GetMapping("/{id}")
    public List<String> findCustomersByUser(@PathVariable("id") Long id) {
        return customerService.findCustomersByUser(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/projects/{id}")
    public List<String> getProjectNames(@PathVariable("id") Long id) {
        return customerService.getProjectNames(id);
    }

    @GetMapping("/all-names")
    public List<String> findAllNames() {
        return customerService.findAllNames();
    }

}
