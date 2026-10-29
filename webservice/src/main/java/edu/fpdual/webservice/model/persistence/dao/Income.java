package edu.fpdual.webservice.model.persistence.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income implements Comparable<Income>{
    private int incomeId;
    private int budgetId;
    private String incomeName;
    private String description;
    private int categoryId;
    private double amount;
    private boolean isRecurrent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp creationDate;

    public Income (ResultSet result) {
        try {
            this.incomeId = result.getInt("incomeId");
            this.budgetId = result.getInt("budgetId");
            this.incomeName = result.getString("incomeName");
            this.description = result.getString("description");
            this.categoryId = result.getInt("categoryId");
            this.amount = result.getDouble("amount");
            this.isRecurrent = result.getBoolean("isRecurrent");
            this.creationDate = result.getTimestamp("creationDate");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Income o) {
        return this.incomeId - o.incomeId;
    }
}
