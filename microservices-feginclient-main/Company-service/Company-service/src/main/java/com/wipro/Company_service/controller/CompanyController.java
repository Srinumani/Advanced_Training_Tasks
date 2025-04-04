package com.wipro.Company_service.controller;

import com.wipro.Company_service.client.Fullresponse;
import com.wipro.Company_service.entity.Company;
import com.wipro.Company_service.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @PostMapping
    public Company createCompany(@RequestBody Company company){
        return companyService.createCompany(company);
    }
    @GetMapping("withProject/{pcode}")
    public Fullresponse findcompanyByPcode(@PathVariable("pcode") Long pcode){
        return companyService.findcomapnyByPcode(pcode);
    }

}
