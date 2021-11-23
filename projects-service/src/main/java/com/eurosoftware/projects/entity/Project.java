package com.eurosoftware.projects.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    @NonNull
    private String name;

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    private Long customerId;

    @NonNull
    public Long getCustomerId() {
        return customerId;
    }

    private double estimatedTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "project")
    private List<ProjectUser> projectUsers = new ArrayList<>();

    public void addUser(ProjectUser user) {
        this.projectUsers.add(user);
    }

    public Project(Long id, String name, Long customerId, double estimatedTime) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.estimatedTime = estimatedTime;
    }

}
