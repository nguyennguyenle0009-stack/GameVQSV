package com.example.webvuonggia.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class providing connections to the PostgreSQL database.
 */
public class DBAccount {

    /** JDBC connection URL for the game database. */
    private static final String url = System.getenv().getOrDefault("DB_URL", "jdbc:postgresql://localhost:5432/gamedb");

    /** Database user name. */
    private static final String user = System.getenv().getOrDefault("DB_USER", "postgres");

    /** Database password. */
    private static final String password = System.getenv().getOrDefault("DB_PASS", "postgres");

    /** Fully qualified class name of the PostgreSQL JDBC driver. */
    private static final String classForName = "org.postgresql.Driver";

    /**
     * Open a new connection to the PostgreSQL database.
     *
     * @return active {@link Connection}
     */
    public static Connection getConnectDB() throws ClassNotFoundException, SQLException {
        Class.forName(classForName);
        return DriverManager.getConnection(url, user, password);
    }
}
