package com.foosey.database;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final Logger LOG = Logger.getLogger(DatabaseUtils.class);

    /*
     * Get the JDBC connection object for Foosey db
     */
    public static Connection getConnection() {
        if (System.getenv("RDS_HOSTNAME") != null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String dbName = System.getenv("RDS_DB_NAME");
                String userName = System.getenv("RDS_USERNAME");
                String password = System.getenv("RDS_PASSWORD");
                String hostname = System.getenv("RDS_HOSTNAME");
                String port = System.getenv("RDS_PORT");
                String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
                LOG.trace("Getting remote connection with connection string from environment variables.");
                Connection con = DriverManager.getConnection(jdbcUrl);
                LOG.info("Remote connection successful.");
                return con;
            }
            catch (ClassNotFoundException | SQLException e) { LOG.warn(e.toString());}
        }
        return null;
    }
}
