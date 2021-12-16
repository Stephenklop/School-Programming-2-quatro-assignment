package com.example.quatroopdracht.data;

import java.sql.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlserver://aei-sql2.avans.nl\\studenten;databaseName=CodeCademy30;user=group30;password=groepje30;portNumber=1443;";

    private Statement statement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;

    // Executes SELECT statements, returns results.
    public CompletableFuture<Void> select(String sql, Consumer<ResultSet> consumer) {
        return CompletableFuture.runAsync(() -> {
            try {
                this.connectDatabase();
                this.resultSet = statement.executeQuery(sql);
                consumer.accept(this.resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeConnection();
            }
        });
    }

    // Executes INSERT statements
    public CompletableFuture<Boolean> insert(String sql) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                this.connectDatabase();
                return this.statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                this.closeConnection();
            }
        });
    }

    // Executes UPDATE statements
    public CompletableFuture<Integer> update(String sql) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                this.connectDatabase();
                return this.statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            } finally {
                this.closeConnection();
            }
        });
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