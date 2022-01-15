package com.example.quatroopdracht.data;

import java.sql.*;
import java.util.function.Consumer;

/**
 * database connectivity manager
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://aei-sql2.avans.nl\\studenten;databaseName=CodeCademy30;user=group30;password=groepje30;portNumber=1443;";

    private Statement statement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;

    // Executes SELECT statements, returns results.
    public void select(String sql, Consumer<ResultSet> consumer) {
        try {
            this.connectDatabase();
            this.resultSet = statement.executeQuery(sql);
            consumer.accept(this.resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    // Executes INSERT statements
    public boolean insert(String sql) {
        try {
            this.connectDatabase();
            this.statement.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeConnection();
        }
    }

    // Executes UPDATE statements
    public int update(String sql) {
        try {
            this.connectDatabase();
            return this.statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            this.closeConnection();
        }
    }

    // Opens database connection
    private void connectDatabase() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        if (this.connection != null) {
            this.statement = this.connection.createStatement();
        }
    }

    // Closes database connections
    private void closeConnection() {
        try {
            if (this.resultSet != null) this.resultSet.close();
            if (this.statement != null) this.statement.close();
            if (this.connection != null) this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}