package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MailService mailService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "admin-dashboard";
    }

    @GetMapping("/employee/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        Employee emp = employeeService.getEmployeeById(id);
        model.addAttribute("employee", emp);
        return "employee-details";
    }

    @PostMapping("/employee/verify")
    public String verifyEmployee(@ModelAttribute("employee") Employee employee) {
        Employee emp = employeeService.getEmployeeById(employee.getId());
        if (emp != null) {
            emp.setVerificationStatus(employee.getVerificationStatus());
            emp.setVerificationComments(employee.getVerificationComments());
            if ("Approved".equalsIgnoreCase(employee.getVerificationStatus())) {
                employeeService.generateEmployeeCredentials(emp);
                mailService.sendApprovalEmail(emp);
            } else if ("Cancelled".equalsIgnoreCase(employee.getVerificationStatus())) {
                mailService.sendCancellationEmail(emp);
            } else if ("Needs Changes".equalsIgnoreCase(employee.getVerificationStatus())) {
                mailService.sendRejectionEmail(emp, employee.getVerificationComments());
            }
            employeeService.saveEmployee(emp);
        }
        return "redirect:/admin/dashboard";
    }
}
