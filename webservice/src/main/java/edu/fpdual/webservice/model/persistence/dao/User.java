package edu.fpdual.webservice.model.persistence.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
public class User implements Comparable<User> {
    private int userId;
    private String userName;
    private String userPassword;
    boolean isAdmin;


    public User(ResultSet resultSet) {
        try {
            this.userId = resultSet.getInt("userId");
            this.userName = resultSet.getString("userName");
            this.userPassword = resultSet.getString("userPassword");
            this.isAdmin = resultSet.getBoolean("isAdmin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(User o) {
        return userId - o.userId;
    }
}
