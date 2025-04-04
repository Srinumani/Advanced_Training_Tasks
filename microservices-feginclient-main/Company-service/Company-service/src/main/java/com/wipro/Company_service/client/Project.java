package com.wipro.Company_service.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    public int id;
    public String name;
    public String description;
    public String status;
    public String start_date;
    public String end_date;
    public String poc;
    public String poc_email;
    public double budget;
    public Long pcode;
}
