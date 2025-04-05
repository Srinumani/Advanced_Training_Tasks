package com.example.employee;

import com.example.employee.model.Employee;
import com.example.employee.model.User;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	// Preload dummy data at startup
	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByUsername("admin").isEmpty()) {
			User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN");
			userRepository.save(admin);
		}
		if (userRepository.findByUsername("employee").isEmpty()) {
			User emp = new User("employee", passwordEncoder.encode("emp123"), "EMPLOYEE");
			userRepository.save(emp);
		}

		if (employeeRepository.count() == 0) {
			Employee employee = new Employee();
			employee.setFullName("John Doe");
			employee.setDateOfBirth(LocalDate.of(1990, 1, 1));
			employee.setDepartment("IT");
			employee.setDesignation("Software Engineer");
			employee.setPhoneNumber("1234567890");
			employee.setAddress("1234 Elm Street");
			employee.setBankAccountNumber("123456789012");
			employee.setIfscCode("IFSC12345");
			employee.setBankName("Sample Bank");
			employee.setAadharNumber("123412341234");
			employee.setCheckNumber("CHK123");
			employee.setCheckMicr("MICR123");
			// Assumed file paths – ensure "uploads" folder exists.
			employee.setPhotoPath("uploads/johndoe.jpg");
			employee.setAadharDocPath("uploads/johndoe_aadhar.pdf");
			employee.setCheckDocPath("uploads/johndoe_check.pdf");
			employee.setVerificationStatus("Pending");
			employeeRepository.save(employee);
		}
	}
}
