package edu.fpdual.webservice.service;

import edu.fpdual.webservice.model.persistence.connector.MySQLConnector;
import edu.fpdual.webservice.model.persistence.dao.Report;
import edu.fpdual.webservice.model.persistence.manager.ReportManager;
import edu.fpdual.webservice.model.persistence.manager.impl.ReportManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReportService {
    private final ReportManager reportManager;

    //@TODO Añadir javadoc
    public ReportService(ReportManagerImpl reportManager) {

        this.reportManager = reportManager;
    }

    public List<Report> findAllReports() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return reportManager.findAll(con);
        }
    }

    public Report findByReportName(String reportName) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return reportManager.findBy(con, "reportName", reportName);
        }
    }

    public boolean deleteReport(Report report) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return reportManager.delete(con, report);
        }
    }

    public boolean createReport(Report report) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return reportManager.create(con, new Report());
        }
    }

    public boolean updateReport(Report report) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return reportManager.update(con, report);
        }
    }
}
