package com.ust.Security_app.controller;

import com.ust.Security_app.config.JwtTokenProvider;
import com.ust.Security_app.dto.*;
import com.ust.Security_app.model.User;
import com.ust.Security_app.service.EmailService;
import com.ust.Security_app.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private EmailService emailService;

    // Login endpoint
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Optional<User> userOpt = userService.findUserByEmail(userDetails.getUsername());
        if (!userOpt.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User not found"));
        }

        User user = userOpt.get();

        return ResponseEntity.ok(new JwtResponse(jwt, user.getId(), user.getName(), user.getEmail(), roles));
    }

    // Signup endpoint
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            User user = new User();
            user.setName(signUpRequest.getName());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(signUpRequest.getPassword());

            Set<String> strRoles = signUpRequest.getRoles();
            Set<String> roles = new HashSet<>();

            if (strRoles == null || strRoles.isEmpty()) {
                roles.add("ROLE_USER");
            } else {
                strRoles.forEach(role -> roles.add(role));
            }

            user.setRoles(roles);
            userService.registerUser(user);

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
    }

    // Forgot password endpoint
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody PasswordResetRequest request) {
        Optional<User> user = userService.findUserByEmail(request.getEmail());

        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email not found"));
        }

        String token = userService.createPasswordResetTokenForUser(user.get());

        try {
            emailService.sendPasswordResetEmail(request.getEmail(), token);
            return ResponseEntity.ok(new MessageResponse("Password reset email sent"));
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error sending email: " + e.getMessage()));
        }
    }

    // Validate reset password token endpoint
    @GetMapping("/reset-password")
    public ResponseEntity<?> validatePasswordResetToken(@RequestParam("token") String token) {
        boolean isValid = userService.validatePasswordResetToken(token);

        if (!isValid) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid or expired token"));
        }

        return ResponseEntity.ok(new MessageResponse("Valid token"));
    }

    // Reset password endpoint
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
                                           @RequestBody Map<String, String> passwordMap) {
        String newPassword = passwordMap.get("password");

        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Password cannot be empty"));
        }

        Optional<User> user = userService.getUserByPasswordResetToken(token);

        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid token"));
        }

        userService.changeUserPassword(user.get(), newPassword);

        return ResponseEntity.ok(new MessageResponse("Password reset successfully"));
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This will render the login.html page
    }

    // Show signup page
    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";  // This will render the signup.html page
    }

    // Show dashboard page
    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "dashboard";  // This will render the dashboard.html page
    }
}
