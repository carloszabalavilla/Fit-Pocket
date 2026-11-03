package edu.fpdual.webapplication.dto;

import edu.fpdual.webapplication.annotation.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Model(type = "Data", version = "1.0", date = "01/06/2023")
public class Report {
    private int reportId;
    private int userId;
    private String reportName;
    private Date reportDate;
    private Category category;
}
