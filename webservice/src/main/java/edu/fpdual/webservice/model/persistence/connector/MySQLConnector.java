package edu.fpdual.webservice.model.persistence.connector;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnector {

    @Setter
    @Getter
    Properties prop = new Properties();

    public MySQLConnector() {
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(prop.getProperty(MySQLConstants.DRIVER));
            String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://127.0.0.1:3306/fitpocket?serverTimezone=UTC");
            return DriverManager.getConnection(url, System.getenv().getOrDefault("DB_USERNAME", ""), System.getenv().getOrDefault("DB_PASSWORD", ""));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getURL() {
        //jdbc:mysql://localhost:1521/world?user=sa&password=admin&useSSL=false;
        return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
                .append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
                .append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
                .append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
                .append(prop.getProperty(MySQLConstants.USER)).append("&password=")
                .append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
                .append(prop.getProperty(MySQLConstants.URL_SSL)).append(("&allowPublicKeyRetrieval="))
                .append(prop.getProperty(MySQLConstants.ALLOW_PUBLIC_KEY_RETRIEVAL)).append(("&useJDBCCompliantTimezoneShift="))
                .append(prop.getProperty(MySQLConstants.USE_JDBC_COMPLIANT_TIMEZONE_SHIFT)).append(("&useLegacyDatetimeCode="))
                .append(prop.getProperty(MySQLConstants.USE_LEGACY_DATE_TIME_CODE)).append(("&serverTimezone="))
                .append(prop.getProperty(MySQLConstants.SERVER_TIMEZONE)).toString();
    }
/*
    public Connection getConnection() {
        return null;
    }
*/
}
