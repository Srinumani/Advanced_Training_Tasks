package com.wipro.Project_service.service;

import com.wipro.Project_service.entity.Project;
import com.wipro.Project_service.repository.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProjcetService {
    @Autowired
    private ProjectRepo repo;
    public Project createProject(Project project) {

        return repo.save(project);
    }

    public List<Project> getProject(Long pcode) {
        return repo.findByPcode(pcode);
    }
}
