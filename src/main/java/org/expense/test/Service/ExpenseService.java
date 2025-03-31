package org.expense.test.Service;

import org.expense.test.entity.Expense;
import org.expense.test.entity.User;
import org.expense.test.repository.ExpenseRepository;
import org.expense.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public Expense addExpense(String userId, BigDecimal amount, String category, String description) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDescription(description);
        expense.setDate(LocalDate.now());

        return expenseRepository.save(expense);
    }
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
    public List<Expense> getUserExpenses(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        return expenseRepository.findByUser(user);
    }
}
