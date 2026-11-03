package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.persistence.connector.MySQLConnector;
import edu.fpdual.webservice.model.persistence.dao.User;
import edu.fpdual.webservice.model.persistence.manager.UserManager;
import edu.fpdual.webservice.model.persistence.manager.impl.UserManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserManager userManager;

    //@TODO Añadir javadoc
    public UserService(UserManagerImpl userManager) {

        this.userManager = userManager;
    }

    public List<User> findAllUsers() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findAll(con);
        }
    }

    public User findByUserName(String userName) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findBy(con, "userName", userName);
        }
    }

    public User findByUserId(int userId) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findBy(con, "userId", userId);
        }
    }

    public User findByUserPassword(String userPassword) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findBy(con, "userPassword", userPassword);
        }
    }

    public boolean deleteUser(User user) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.delete(con, user);
        }
    }

    public boolean createUser(User user) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.create(con, user);
        }
    }

    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.update(con, user);
        }
    }
}
