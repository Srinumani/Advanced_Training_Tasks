package com.ust.Security_app.controller;

import com.ust.Security_app.config.JwtTokenProvider;
import com.ust.Security_app.model.Expense;
import com.ust.Security_app.service.ExpenseService;
import com.ust.Security_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // Get all expenses endpoint
    @GetMapping("/list")
    public ResponseEntity<List<Expense>> getAllExpenses(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7); // Removing "Bearer " from token
        if (!jwtTokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    // Add a new expense endpoint
    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestHeader("Authorization") String token,
                                              @RequestBody Expense expense) {
        String jwt = token.substring(7); // Removing "Bearer " from token
        if (!jwtTokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Expense savedExpense = expenseService.addExpense(expense);
        return ResponseEntity.ok(savedExpense);
    }

    // Get expense summary for a specific month
    @GetMapping("/summary/{month}")
    public ResponseEntity<Map<String, Double>> getExpenseSummary(@RequestHeader("Authorization") String token,
                                                                 @PathVariable int month) {
        String jwt = token.substring(7); // Removing "Bearer " from token
        if (!jwtTokenProvider.validateToken(jwt)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Map<String, Double> summary = expenseService.getExpenseSummary(month);
        return ResponseEntity.ok(summary);
    }
}
