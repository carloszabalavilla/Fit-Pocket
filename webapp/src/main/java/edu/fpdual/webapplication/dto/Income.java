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
public class Income {
    private int budgetId;
    private int incomeId;
    private String incomeName;
    private String description;
    private int categoryId;
    private double amount;
    private boolean isRecurrent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp creationDate;

    @Builder
    public Income(int budgetId, String incomeName, String description, int categoryId, double amount, boolean isRecurrent) {
        this.budgetId = budgetId;
        this.incomeName = incomeName;
        this.description = description;
        this.categoryId = categoryId;
        this.amount = amount;
        this.isRecurrent = isRecurrent;
    }
}
