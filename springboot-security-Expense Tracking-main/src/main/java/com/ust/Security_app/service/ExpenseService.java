package com.ust.Security_app.service;

import com.ust.Security_app.model.Expense;
import com.ust.Security_app.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Map<String, Double> getExpenseSummary(int month) {
        // Get the current date to determine the current year
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();

        // Filter expenses based on the given month and year
        List<Expense> allExpenses = expenseRepository.findAll();
        List<Expense> monthlyExpenses = allExpenses.stream()
                .filter(expense -> expense.getDate().getMonthValue() == month && expense.getDate().getYear() == currentYear)
                .collect(Collectors.toList());

        // Calculate the totals for the requested month
        double monthlyTotal = monthlyExpenses.stream().mapToDouble(Expense::getAmount).sum();

        // For quarterly and yearly totals, filter based on the current year and calculate totals
        int quarter = (month - 1) / 3 + 1; // Calculate quarter (1 = Jan-Mar, 2 = Apr-Jun, etc.)

        // Filter expenses for the quarter
        List<Expense> quarterlyExpenses = allExpenses.stream()
                .filter(expense -> expense.getDate().getYear() == currentYear &&
                        (expense.getDate().getMonthValue() - 1) / 3 + 1 == quarter)
                .collect(Collectors.toList());
        double quarterlyTotal = quarterlyExpenses.stream().mapToDouble(Expense::getAmount).sum();

        // Filter expenses for the current year
        List<Expense> yearlyExpenses = allExpenses.stream()
                .filter(expense -> expense.getDate().getYear() == currentYear)
                .collect(Collectors.toList());
        double yearlyTotal = yearlyExpenses.stream().mapToDouble(Expense::getAmount).sum();

        // Prepare summary
        Map<String, Double> summary = new HashMap<>();
        summary.put("Monthly Total", monthlyTotal);
        summary.put("Quarterly Total", quarterlyTotal);
        summary.put("Yearly Total", yearlyTotal);

        return summary;
    }
}
