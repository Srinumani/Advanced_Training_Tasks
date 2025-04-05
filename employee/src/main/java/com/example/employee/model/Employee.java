package com.example.employee.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Full name is required")
    private String fullName;

    private LocalDate dateOfBirth;
    private String department;
    private String designation;
    private String phoneNumber;
    private String address;

    // Bank details
    private String bankAccountNumber;
    private String ifscCode;
    private String bankName;

    // Document details
    private String aadharNumber;
    private String checkNumber;
    private String checkMicr;

    // File paths for uploads
    private String photoPath;
    private String aadharDocPath;
    private String checkDocPath;

    // Generated credentials after approval
    private String employeeId;
    private String employeeEmail;

    // Verification status and admin comments
    private String verificationStatus; // "Pending", "Approved", "Cancelled", "Needs Changes"
    private String verificationComments;

    public Employee() {
        this.verificationStatus = "Pending";
    }

    // Getters and Setters (generate using your IDE or Lombok)
    // … (for brevity, all getters and setters should be here)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getBankAccountNumber() { return bankAccountNumber; }
    public void setBankAccountNumber(String bankAccountNumber) { this.bankAccountNumber = bankAccountNumber; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }

    public String getCheckNumber() { return checkNumber; }
    public void setCheckNumber(String checkNumber) { this.checkNumber = checkNumber; }

    public String getCheckMicr() { return checkMicr; }
    public void setCheckMicr(String checkMicr) { this.checkMicr = checkMicr; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

    public String getAadharDocPath() { return aadharDocPath; }
    public void setAadharDocPath(String aadharDocPath) { this.aadharDocPath = aadharDocPath; }

    public String getCheckDocPath() { return checkDocPath; }
    public void setCheckDocPath(String checkDocPath) { this.checkDocPath = checkDocPath; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getEmployeeEmail() { return employeeEmail; }
    public void setEmployeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; }

    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }

    public String getVerificationComments() { return verificationComments; }
    public void setVerificationComments(String verificationComments) { this.verificationComments = verificationComments; }
}
