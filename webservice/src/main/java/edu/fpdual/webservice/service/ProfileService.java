package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.persistence.connector.MySQLConnector;
import edu.fpdual.webservice.model.persistence.dao.Profile;
import edu.fpdual.webservice.model.persistence.manager.ProfileManager;
import edu.fpdual.webservice.model.persistence.manager.impl.ProfileManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProfileService {
    private final ProfileManager profileManager;

    //@TODO Añadir javadoc
    public ProfileService(ProfileManagerImpl profileManager) {

        this.profileManager = profileManager;
    }

    public List<Profile> findAllProfiles() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return profileManager.findAll(con);
        }
    }

    public Profile findByUserId(int userId) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return profileManager.findBy(con, "userId", userId);
        }
    }

    public Profile findByEmail(String email) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return profileManager.findBy(con, "email", email);
        }
    }

    public boolean deleteProfile(Profile profile) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return profileManager.delete(con, profile);
        }
    }

    public boolean createProfile(Profile profile) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return profileManager.create(con, profile);
        }
    }

    public boolean updateProfile(Profile profile) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return profileManager.update(con, profile);
        }
    }
}
