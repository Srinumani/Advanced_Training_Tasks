package com.ust.Security_app.repository;

import com.ust.Security_app.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// ExpenseRepository.java
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE MONTH(e.date) = :month")
    Double calculateMonthlyTotal();

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE QUARTER(e.date) = :quarter")
    Double calculateQuarterlyTotal();

    @Query("SELECT SUM(e.amount) FROM Expense e WHERE YEAR(e.date) = :year")
    Double calculateYearlyTotal();
}

