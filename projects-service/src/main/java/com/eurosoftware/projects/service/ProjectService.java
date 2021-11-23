package com.eurosoftware.projects.service;

import com.eurosoftware.projects.DTOs.ProjectFinanceDto;
import com.eurosoftware.projects.DTOs.ProjectWithFinanceDto;
import com.eurosoftware.projects.entity.Project;
import com.eurosoftware.projects.entity.ProjectUser;
import com.eurosoftware.projects.repository.ProjectRepository;
import com.eurosoftware.projects.repository.ProjectUserRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectUserRepository projectUserRepository;

    private final RestTemplate restTemplate;

    public ProjectService(ProjectRepository projectRepository, ProjectUserRepository projectUserRepository, RestTemplate restTemplate) {
        this.projectRepository = projectRepository;
        this.projectUserRepository = projectUserRepository;
        this.restTemplate = restTemplate;
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public ProjectUser saveUser(ProjectUser user) {
        return projectUserRepository.save(user);
    }

    public List<Long> findCustomersIdByUser(Long id) {
        var result = new ArrayList<Long>();
        var projectUsers = projectUserRepository.findAllByUserId(id);
        for(var item : projectUsers) {
            result.add(item.getProject().getCustomerId());
        }
        return result;
    }

    public List<Long> findProjectsByCustomer(Long id) {
        var result = new ArrayList<Long>();
        var projects = projectRepository.findAllByCustomerId(id);
        for(var item : projects) result.add(item.getId());
        return result;
    }

    public void deleteProjectsByCustomer(Long id) {
        var projects = projectRepository.findAllByCustomerId(id);
        var toDelete = new ArrayList<Long>();
        for(var item : projects) toDelete.add(item.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(toDelete, headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange("http://localhost:9002/delete-by-projects/", HttpMethod.DELETE, requestEntity, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200) return;

        projectRepository.deleteAllById(toDelete);
    }

    public List<String> findNamesByCustomer(Long id) {
        var list = new ArrayList<String>();
        var projects = projectRepository.findAllByCustomerId(id);

        for(var item : projects) list.add(item.getName());

        return list;
    }

    public ProjectWithFinanceDto findProjectFinances(Long id) {
        var result = new ProjectWithFinanceDto();
        var project = projectRepository.findById(id).orElse(null);

        if(project == null) return null;

        result.setProjectName(project.getName());
        result.setId(project.getId());

        ResponseEntity<List<ProjectFinanceDto>> responseEntity = restTemplate.exchange("http://localhost:9002/by-project/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        if(responseEntity.getStatusCode().value() != 200 || !responseEntity.hasBody()) return result;

        List<ProjectFinanceDto> listOfFinances = responseEntity.getBody();

        if(listOfFinances == null) return result;

        result.setFinance(listOfFinances);

        return result;
    }

    public List<String> findAllNames() {
        var list = new ArrayList<String>();
        var projects = projectRepository.findAll();
        for(var item : projects) list.add(item.getName());
        return list;
    }

}
