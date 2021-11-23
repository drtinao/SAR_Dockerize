package com.eurosoftware.customers.service;

import com.eurosoftware.customers.entity.Customer;
import com.eurosoftware.customers.repository.CustomerRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    public Customer save(Customer user) {
        return customerRepository.save(user);
    }

    public List<String> findCustomersByUser(Long id) {
        var result = new ArrayList<String>();

        ResponseEntity<List<Long>> responseEntity = restTemplate.exchange("http://localhost:9003/project/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return result;

        List<Long> listOfIds = responseEntity.getBody();

        if(listOfIds == null) return result;

        var customers = customerRepository.findAllById(listOfIds);

        for(var item : customers) result.add(item.getName());

        return result;
    }

    public void deleteCustomer(Long id) {
        var customer = customerRepository.findById(id).orElse(null);

        if(customer == null) return;

        ResponseEntity<List<Long>> responseEntity = restTemplate.exchange("http://localhost:9003/by-customer/" + id, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200) return;

        customerRepository.deleteById(id);
    }

    public List<String> getProjectNames(Long id) {
        var list = new ArrayList<String>();
        var customer = customerRepository.findById(id).orElse(null);

        if(customer == null) return list;

        ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://localhost:9003/names-by-customer/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200) return list;

        List<String> listOfNames = responseEntity.getBody();

        if(listOfNames == null) return list;

        return listOfNames;
    }

    public List<String> findAllNames() {
        var list = new ArrayList<String>();
        var customers = customerRepository.findAll();
        for(var item : customers) list.add(item.getName());
        return list;
    }

}
