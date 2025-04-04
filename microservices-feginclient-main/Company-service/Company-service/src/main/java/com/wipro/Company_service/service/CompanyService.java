package com.wipro.Company_service.service;

import com.wipro.Company_service.client.Fullresponse;
import com.wipro.Company_service.client.Project;
import com.wipro.Company_service.entity.Company;
import com.wipro.Company_service.fegin.ProjectClient;
import com.wipro.Company_service.repository.Companyrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompanyService {
    @Autowired
    private Companyrepository companyrepository;
    @Autowired
    private ProjectClient client;
    public Company createCompany(Company company) {
        return companyrepository.save(company);
    }

    public Fullresponse findcomapnyByPcode(Long pcode) {
        Company company=companyrepository.findByPcode(pcode).orElse(null);
        List<Project> list=client.findProjectByPcode(pcode);
        Fullresponse  res=new Fullresponse();
        res.setCompanyname(company.getCompanyname());
        res.setPcode(company.getPcode());
        res.setCeo(company.getCeo());
        res.setTurnover(company.getTurnover());
        res.setWebsite(company.getWebsite());
        res.setStockexchange(company.getStockexchange());
        res.setCompanycontact(company.getCompanycontact());
        res.setCompanyAddress(company.getCompanyAddress());
        res.setProjectList(list);
        return res;




    }
}
