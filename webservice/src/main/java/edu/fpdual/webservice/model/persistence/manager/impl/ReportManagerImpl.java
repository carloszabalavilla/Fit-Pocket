package edu.fpdual.webservice.model.persistence.manager.impl;

import edu.fpdual.webservice.model.persistence.dao.Report;
import edu.fpdual.webservice.model.persistence.manager.ReportManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportManagerImpl implements ReportManager {
    final String tableName = "REPORT";

    //@TODO AÑADIR JAVADOC

    public List<Report> findAll(Connection con) {
        List<Report> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName;

        try (Statement stm = con.createStatement()) {
            ResultSet result = stm.executeQuery(query);
            while (result.next()) {
                entities.add(new Report(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public List<Report> findAllBy(Connection con, String fieldName, Object value) {
        List<Report> entities = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                entities.add(new Report(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entities = null;
        }
        return entities;
    }

    @Override
    public Report findBy(Connection con, String fieldName, Object value) {
        Report entity = new Report();
        String query = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            if (value.getClass().equals(String.class)) {
                value = ((String) value).toLowerCase();
            }
            stm.setObject(1, value);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                entity = new Report(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            entity = null;
        }
        return entity;
    }

    @Override
    public boolean delete(Connection con, Report entity) {
        boolean result;
        String query = "DELETE FROM " + tableName + " WHERE userName" + " = ?";

        try (PreparedStatement stm = con.prepareStatement(query)) {
            stm.setObject(1, entity.getReportId());
            result = stm.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     * Cuando creas un usuario, el return del método debe de ser 4.
     */
    @Override
    public boolean create(Connection con, Report entity) {
        boolean result;
        String query = "INSERT INTO " + tableName + " (userName, userPassword, admn) values(?,?,?)";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stm.setInt(1, entity.getReportId());

            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /**
     *
     */
    @Override
    public boolean update(Connection con, Report entity) {
        boolean result;
        String query = "UPDATE " + tableName + " SET userName = ? , userPassword = ? WHERE userId = ?";

        try (PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, entity.getReportId());


            result = stm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

}
