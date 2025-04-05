package com.example.employee.controller;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private final String UPLOAD_DIR = "C:\\Users\\Administrator\\Desktop\\Advance Training Projects\\check\\employee\\uploads";

    @GetMapping("/dashboard")
    public String employeeDashboard(Model model) {
        model.addAttribute("message", "Welcome to your dashboard!");
        return "employee-dashboard";
    }

    @GetMapping("/form")
    public String showEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }
    @PostMapping("/submit")
    public String submitEmployeeForm(@Valid @ModelAttribute("employee") Employee employee,
                                     BindingResult bindingResult,
                                     @RequestParam("photo") MultipartFile photo,
                                     @RequestParam("aadharDoc") MultipartFile aadharDoc,
                                     @RequestParam("checkDoc") MultipartFile checkDoc,
                                     Model model) {
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }
        // Ensure the upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        try {
            if (!photo.isEmpty()) {
                String photoPath = UPLOAD_DIR + File.separator + photo.getOriginalFilename();
                photo.transferTo(new File(photoPath));
                employee.setPhotoPath(photoPath);
            }
            if (!aadharDoc.isEmpty()) {
                String aadharPath = UPLOAD_DIR + File.separator + aadharDoc.getOriginalFilename();
                aadharDoc.transferTo(new File(aadharPath));
                employee.setAadharDocPath(aadharPath);
            }
            if (!checkDoc.isEmpty()) {
                String checkPath = UPLOAD_DIR + File.separator + checkDoc.getOriginalFilename();
                checkDoc.transferTo(new File(checkPath));
                employee.setCheckDocPath(checkPath);
            }
        } catch (IOException e) {
            model.addAttribute("fileUploadError", "Error uploading files: " + e.getMessage());
            return "employee-form";
        }
        employeeService.saveEmployee(employee);
        model.addAttribute("message", "Submission successful! Your details are pending approval.");
        return "employee-dashboard";
    }

}
