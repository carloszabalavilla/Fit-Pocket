package edu.fpdual.webapplication.dto;

import edu.fpdual.webapplication.annotation.Model;
import edu.fpdual.webapplication.utilities.InvalidPasswordException;
import edu.fpdual.webapplication.utilities.Password;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Model(type = "Data", version = "1.0", date = "01/06/2023")
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private boolean isAdmin;

    public User(String userName, String userPassword, boolean isAdmin) throws InvalidPasswordException {
        this.userName = userName;
        this.userPassword = new Password(userPassword, userName).toString();
        this.isAdmin = isAdmin;
    }
}
