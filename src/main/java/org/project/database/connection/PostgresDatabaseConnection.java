package org.project.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Postgres database connection.
 */
public class PostgresDatabaseConnection implements DatabaseConnection {
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String DATABASE_URL = System.getenv("Env_database_url");
    private static final String DATABASE_USERNAME = "Visnja";
    private static final String DATABASE_PASSWORD = System.getenv("Env_database_password");

    /**
     * Gets connection.
     *
     * @return the connection
     */
    @Override
    public Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Close connection.
     *
     * @param connection the connection
     */
    @Override
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
