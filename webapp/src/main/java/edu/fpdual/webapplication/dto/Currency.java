package edu.fpdual.webapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private int currencyId;
    private String currencyName;
    private String currencySymbol;

    public Currency(String currencyName, String currencySymbol) {
        this.currencyName = currencyName;
        this.currencySymbol = currencySymbol;
    }

}
