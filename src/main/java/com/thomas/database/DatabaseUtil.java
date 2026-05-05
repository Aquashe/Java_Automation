package com.thomas.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseUtil {
    private static final Logger logger = Logger.getLogger(DatabaseUtil.class.getName());
    private static final Properties dbProperties;

    static {
        dbProperties = new Properties();
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                throw new RuntimeException("db.properties not found in classpath");
            }
            dbProperties.load(input);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load database properties", e);
            throw new RuntimeException(e);
        }
    }

    public static String[] getCredentials(String scenario) {
        String host = dbProperties.getProperty("db.host");
        String port = dbProperties.getProperty("db.port");
        String database = dbProperties.getProperty("db.database");
        String username = dbProperties.getProperty("db.username");
        String password = dbProperties.getProperty("db.password");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        String[] credentials = new String[2];
        String query = "SELECT username, password FROM credentials WHERE scenario = ?";

        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, scenario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    credentials[0] = rs.getString("username");
                    credentials[1] = rs.getString("password");
                } else {
                    logger.warning("No credentials found for scenario: " + scenario);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error while fetching credentials", e);
            throw new RuntimeException(e);
        }

        return credentials;
    }
}
