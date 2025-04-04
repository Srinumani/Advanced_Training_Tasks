package com.wipro.Company_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Company {
    @Id
    public Long pcode;
    public String companyname;
    public String ceo;
    public String turnover;
    public String website;
    public String stockexchange;
    public String companycontact;
    public String companyAddress;

    public Company() {
    }

    public Company(Long pcode, String companyname, String ceo, String turnover, String website, String stockexchange, String companycontact, String companyAddress) {
        this.pcode = pcode;
        this.companyname = companyname;
        this.ceo = ceo;
        this.turnover = turnover;
        this.website = website;
        this.stockexchange = stockexchange;
        this.companycontact = companycontact;
        this.companyAddress = companyAddress;
    }

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
}
