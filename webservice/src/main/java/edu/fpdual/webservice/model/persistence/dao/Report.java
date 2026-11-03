package edu.fpdual.webservice.model.persistence.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Data
@NoArgsConstructor
public class Report {

    private int reportId;
    private int userId;
    private String reportName;
    private Date reportDate;
    private Category category;

    public Report(ResultSet result) {
        try {
            this.reportId = result.getInt("reportId");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
