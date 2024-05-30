package org.project.database.connection;

import java.sql.Connection;

/**
 * The interface Database connection.
 */
public interface DatabaseConnection {
    Connection getConnection();
    void closeConnection(Connection connection);
}
