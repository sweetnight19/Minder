package Persistance.SQL;

import java.sql.*;

import Persistance.ConfigurationDAO;

/**
 * The type Sql connector.
 */
public class SQLConnector {
    private static SQLConnector instance = null;
    private final String username;
    private final String password;
    private final String url;
    private Connection conn;

    /**
     * Gets instance.
     *
     * @param confDAO the conf dao
     * @return the instance
     */
    public static SQLConnector getInstance(ConfigurationDAO confDAO) {
        if (instance == null) {
            instance = new SQLConnector(confDAO.getUsername(), confDAO.getPassword(), confDAO.getIp(),
                    confDAO.getPort(), confDAO.getDatabase());
            instance.connect();
        }
        return instance;
    }

    private SQLConnector(String username, String password, String ip, int port, String database) {
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
    }

    /**
     * Connect.
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
    }

    /**
     * Insert query boolean.
     *
     * @param query the query
     * @return the boolean
     */
    public boolean insertQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            return false;
        }
    }

    /**
     * Update query boolean.
     *
     * @param query the query
     * @return the boolean
     */
    public boolean updateQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problema when updating --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            return false;
        }
    }

    /**
     * Delete query boolean.
     *
     * @param query the query
     * @return the boolean
     */
    public boolean deleteQuery(String query) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when deleting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
            return false;
        }

    }

    /**
     * Select query result set.
     *
     * @param query the query
     * @return the result set
     */
    public ResultSet selectQuery(String query) {
        ResultSet rs = null;
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return rs;
    }

    /**
     * Disconnect.
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println(
                    "Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }
}