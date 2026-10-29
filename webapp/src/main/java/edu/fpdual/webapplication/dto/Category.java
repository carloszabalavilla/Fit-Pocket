package edu.fpdual.webapplication.dto;

import edu.fpdual.webapplication.annotation.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Model(type = "Data", version = "1.0", date = "01/06/2023")
public class Category {
    private int categoryId;
    private int userId;
    private String categoryName;

    public Category(int userId, String categoryName) {
        this.userId = userId;
        this.categoryName = categoryName;
    }
}
