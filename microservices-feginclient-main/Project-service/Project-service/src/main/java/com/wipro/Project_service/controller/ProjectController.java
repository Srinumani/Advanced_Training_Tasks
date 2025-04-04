package com.wipro.Project_service.controller;

import com.wipro.Project_service.entity.Project;
import com.wipro.Project_service.service.ProjcetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjcetService service;
    @PostMapping
    public Project createProject(@RequestBody Project project){
       return service.createProject(project);
    }

    @GetMapping("company/{pcode}")
    public List<Project> getProject(@PathVariable Long pcode){
        return service.getProject(pcode);
    }


}
