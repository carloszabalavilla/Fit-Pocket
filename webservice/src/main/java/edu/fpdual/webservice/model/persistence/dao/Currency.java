package edu.fpdual.webservice.model.persistence.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private int currencyId;
    private String currencyName;
    private String currencySymbol;

    public Currency(ResultSet result) {
        try {
            this.currencyId = result.getInt("currencyID");
            this.currencyName = result.getString("currencyName");
            this.currencySymbol = result.getString("currencySymbol");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
