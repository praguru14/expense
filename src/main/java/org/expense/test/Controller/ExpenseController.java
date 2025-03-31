package org.expense.test.Controller;

import org.expense.test.DTO.ExpenseRequest;
import org.expense.test.Service.ExpenseService;
import org.expense.test.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestBody ExpenseRequest request) {
        Expense expense = expenseService.addExpense(request.getUserId(), request.getAmount(),
                request.getCategory(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> getUserExpenses(@PathVariable String userId) {
        List<Expense> expenses = expenseService.getUserExpenses(userId);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
}
