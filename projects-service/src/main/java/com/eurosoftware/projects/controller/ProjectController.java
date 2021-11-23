package com.eurosoftware.projects.controller;

import com.eurosoftware.projects.DTOs.ProjectFinanceDto;
import com.eurosoftware.projects.DTOs.ProjectWithFinanceDto;
import com.eurosoftware.projects.entity.Project;
import com.eurosoftware.projects.entity.ProjectUser;
import com.eurosoftware.projects.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        var project1 = new Project(1L, "Projekt 1", 1L, 25.6);
        var project2 = new Project(2L, "Projekt 2", 1L, 25.6);
        var project3 = new Project(3L, "Projekt 3", 1L, 25.6);
        var project4 = new Project(4L, "Projekt 4", 2L, 25.6);
        var project5 = new Project(5L, "Projekt 5", 2L, 25.6);
        var project6 = new Project(6L, "Projekt 6", 3L, 25.6);
        var project7 = new Project(7L, "Projekt 7", 4L, 25.6);
        var project8 = new Project(8L, "Projekt 8", 4L, 25.6);
        var project9 = new Project(9L, "Projekt 9", 5L, 25.6);
        var project10 = new Project(10L, "Projekt 10", 6L, 25.6);
        var project11 = new Project(11L, "Projekt 11", 6L, 25.6);
        var project12 = new Project(12L, "Projekt 12", 6L, 25.6);
        var project13 = new Project(13L, "Projekt 13", 5L, 25.6);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);
        projectService.save(project4);
        projectService.save(project5);
        projectService.save(project6);
        projectService.save(project7);
        projectService.save(project8);
        projectService.save(project9);
        projectService.save(project10);
        projectService.save(project11);
        projectService.save(project12);
        projectService.save(project13);

        projectService.saveUser(new ProjectUser(1L, project1, 1L));
        projectService.saveUser(new ProjectUser(2L, project1, 2L));
        projectService.saveUser(new ProjectUser(3L, project1, 3L));
        projectService.saveUser(new ProjectUser(4L, project2, 5L));
        projectService.saveUser(new ProjectUser(5L, project3, 6L));
        projectService.saveUser(new ProjectUser(6L, project4, 8L));
        projectService.saveUser(new ProjectUser(7L, project5, 7L));
        projectService.saveUser(new ProjectUser(8L, project6, 4L));
        projectService.saveUser(new ProjectUser(9L, project7, 2L));
        projectService.saveUser(new ProjectUser(10L, project8, 1L));
        projectService.saveUser(new ProjectUser(11L, project9, 1L));
        projectService.saveUser(new ProjectUser(12L, project10, 6L));
        projectService.saveUser(new ProjectUser(13L, project11, 1L));
        projectService.saveUser(new ProjectUser(14L, project12, 1L));
        projectService.saveUser(new ProjectUser(15L, project13, 1L));
        projectService.saveUser(new ProjectUser(16L, project11, 5L));
        projectService.saveUser(new ProjectUser(17L, project11, 7L));
        projectService.saveUser(new ProjectUser(18L, project9, 4L));
        projectService.saveUser(new ProjectUser(19L, project9, 6L));
        projectService.saveUser(new ProjectUser(20L, project8, 6L));
        projectService.saveUser(new ProjectUser(21L, project7, 8L));
        projectService.saveUser(new ProjectUser(22L, project6, 2L));
    }

    @PostMapping("/")
    public Project saveProject(@RequestBody Project user) {
        return projectService.save(user);
    }

    @GetMapping("/{id}")
    public List<Long> findCustomersIdByUser(@PathVariable("id") Long id) {
        return projectService.findCustomersIdByUser(id);
    }

    @GetMapping("/by-customer/{id}")
    public List<Long> findProjectsByCustomer(@PathVariable("id") Long id) {
        return projectService.findProjectsByCustomer(id);
    }

    @DeleteMapping("/by-customer/{id}")
    public void deleteProjectByCustomer(@PathVariable("id") Long id) {
        projectService.deleteProjectsByCustomer(id);
    }

    @GetMapping("/names-by-customer/{id}")
    public List<String> findNamesByCustomer(@PathVariable("id") Long id) {
        return projectService.findNamesByCustomer(id);
    }

    @GetMapping("/with-finance/{id}")
    public ProjectWithFinanceDto findProjectWithFinance(@PathVariable("id") Long id) {
        return projectService.findProjectFinances(id);
    }

    @GetMapping("/all-names")
    public List<String> findAllNames() {
        return projectService.findAllNames();
    }
}
