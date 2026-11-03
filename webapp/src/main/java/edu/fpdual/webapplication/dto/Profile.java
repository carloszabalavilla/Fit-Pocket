package edu.fpdual.webapplication.dto;

import edu.fpdual.webapplication.annotation.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Model(type = "Data", version = "1.0", date = "01/06/2023")
public class Profile {
    private int userId;
    private String description;
    private String email;
    private String link;
    private String location;
    private int phone;

    public Profile(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
