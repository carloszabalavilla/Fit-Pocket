package edu.fpdual.webapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.fpdual.webapplication.annotation.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Model(type = "Data", version = "1.0", date = "01/06/2023")
public class Expense {
    private int budgetId;
    private int expenseId;
    private String expenseName;
    private String description;
    private int categoryId;
    private double amount;
    private boolean isRecurrent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp creationDate;

    @Builder
    public Expense(int budgetId, String expenseName, String description, int categoryId, double amount, boolean isRecurrent) {
        this.budgetId = budgetId;
        this.expenseName = expenseName;
        this.description = description;
        this.categoryId = categoryId;
        this.amount = amount;
        this.isRecurrent = isRecurrent;
    }
}
