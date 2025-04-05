package com.example.employee.service;

import com.example.employee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    // Generic send notification
    public void sendNotification(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    // When approved, send credentials email
    public void sendApprovalEmail(Employee employee) {
        String subject = "Approval Notification: Employee Credentials";
        String message = "Dear " + employee.getFullName() + ",\n\n"
                + "Your registration has been approved!\n"
                + "Employee ID: " + employee.getEmployeeId() + "\n"
                + "Company Email: " + employee.getEmployeeEmail() + "\n\n"
                + "Welcome aboard!\nHR Department";
        sendNotification(employee.getEmployeeEmail(), subject, message);
    }

    // When changes are required, send rejection email with comments
    public void sendRejectionEmail(Employee employee, String comments) {
        String subject = "Action Required: Update Your Submission";
        String message = "Dear " + employee.getFullName() + ",\n\n"
                + "Your submission requires some changes.\n"
                + "Admin Comments: " + comments + "\n\n"
                + "Please update your details accordingly.\nHR Department";
        sendNotification(employee.getEmployeeEmail(), subject, message);
    }

    // When cancelled, send cancellation email
    public void sendCancellationEmail(Employee employee) {
        String subject = "Submission Cancelled";
        String message = "Dear " + employee.getFullName() + ",\n\n"
                + "Your submission has been cancelled. For further details, please contact HR.\n\n"
                + "Regards,\nHR Department";
        sendNotification(employee.getEmployeeEmail(), subject, message);
    }
}
