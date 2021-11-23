package com.eurosoftware.projects.repository;

import com.eurosoftware.projects.entity.Project;
import com.eurosoftware.projects.entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectUserRepository extends JpaRepository<ProjectUser, Long> {

    List<ProjectUser> findAllByUserId(Long customerId);

}