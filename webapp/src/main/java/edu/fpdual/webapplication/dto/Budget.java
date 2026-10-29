package edu.fpdual.webapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.fpdual.webapplication.annotation.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Model(type = "Data", version = "1.0", date = "01/06/2023")
public class Budget {
    private int userId;
    private int budgetId;
    private String budgetName;
    private String description;
    private int currencyId;
    private List<Expense> expenseList;
    private List<Income> incomeList;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp creationDate;

    @Builder
    public Budget(int userId, String budgetName, String description, int currencyId) {
        this.userId = userId;
        this.budgetName = budgetName;
        this.description = description;
        this.currencyId = currencyId;
        this.expenseList = new ArrayList<>();
        this.incomeList = new ArrayList<>();
    }

}
