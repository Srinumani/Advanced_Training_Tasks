package com.wipro.Company_service.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fullresponse {
    public Long pcode;
    public String companyname;
    public String ceo;
    public String turnover;
    public String website;
    public String stockexchange;
    public String companycontact;
    public String companyAddress;
    public List<Project> projectList;

    public Long getPcode() {
        return pcode;
    }

    public void setPcode(Long pcode) {
        this.pcode = pcode;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStockexchange() {
        return stockexchange;
    }

    public void setStockexchange(String stockexchange) {
        this.stockexchange = stockexchange;
    }

    public String getCompanycontact() {
        return companycontact;
    }

    public void setCompanycontact(String companycontact) {
        this.companycontact = companycontact;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
}
