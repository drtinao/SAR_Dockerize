package com.eurosoftware.users.service;

import com.eurosoftware.users.DTOs.NonSenseDto;
import com.eurosoftware.users.DTOs.PropertyDto;
import com.eurosoftware.users.DTOs.UserOverviewDto;
import com.eurosoftware.users.entity.User;
import com.eurosoftware.users.repository.UserRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserOverviewDto getProperties(Long id) {
        var user = userRepository.findById(id).orElse(null);

        if(user == null) return null;

        var result = new UserOverviewDto();

        result.setId(user.getId());
        result.setName(user.getName());

        ResponseEntity<List<PropertyDto>> responseEntity = restTemplate.exchange("http://localhost:9004/property/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return result;

        List<PropertyDto> listOfProperties = responseEntity.getBody();
        result.setProperties(listOfProperties);

        return result;
    }

    public List<String> getAssignedCustomers(Long id) {
        var list = new ArrayList<String>();
        var user = userRepository.findById(id).orElse(null);

        if(user == null) return list;

        ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://localhost:9001/customer/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return list;

        List<String> listOfCustomers = responseEntity.getBody();

        if(listOfCustomers == null) return list;

        return listOfCustomers;
    }

    private List<String> fetchAllProperties() {
        var list = new ArrayList<String>();
        ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://localhost:9004/property/all-names", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return list;

        List<String> listOfProperties = responseEntity.getBody();

        if(listOfProperties == null) return list;

        return listOfProperties;
    }

    private List<String> fetchAllCustomers() {
        var list = new ArrayList<String>();
        ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://localhost:9001/customers/all-names", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return list;

        List<String> listOfCustomers = responseEntity.getBody();

        if(listOfCustomers == null) return list;

        return listOfCustomers;
    }

    private List<Double> fetchAllFinance() {
        var list = new ArrayList<Double>();
        ResponseEntity<List<Double>> responseEntity = restTemplate.exchange("http://localhost:9002/finance/all-names", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return list;

        List<Double> listOfFinance = responseEntity.getBody();

        if(listOfFinance == null) return list;

        return listOfFinance;
    }

    private List<String> fetchAllUsers() {
        var list = new ArrayList<String>();
        var users = userRepository.findAll();
        for(var item : users) {
            list.add(item.getName());
        }
        return list;
    }

    private List<String> fetchAllProjects() {
        var list = new ArrayList<String>();
        ResponseEntity<List<String>> responseEntity = restTemplate.exchange("http://localhost:9003/projects/all-names", HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return list;

        List<String> listOfProjects = responseEntity.getBody();

        if(listOfProjects == null) return list;

        return listOfProjects;
    }

    public NonSenseDto getNonsenseData() {
        var result = new NonSenseDto();
        result.setProperties(fetchAllProperties());
        result.setCustomers(fetchAllCustomers());
        result.setFinance(fetchAllFinance());
        result.setUsers(fetchAllUsers());
        result.setProjects(fetchAllProjects());
        return result;
    }
}
