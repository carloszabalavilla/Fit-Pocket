package edu.fpdual.webapplication.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseIncome {
    private String fecha;
    private String tipo;
    private String cantidad;
    private String categoria;
}
