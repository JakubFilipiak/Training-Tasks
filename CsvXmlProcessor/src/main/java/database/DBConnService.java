package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jakub Filipiak on 24.05.2019.
 */
public class DBConnService {

    private static final Logger LOGGER = Logger.getLogger(DBConnService.class.getName());

    private DBAccessData dbAccessData = DBAccessData.INSTANCE;

    private final String url = dbAccessData.getUrl();
    private final String username = dbAccessData.getUsername();
    private final String password = dbAccessData.getPassword();

    public void connect() {

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
            SingletonDBConnection.INSTANCE.setConnection(connection);
            System.out.println("Connected to PostgreSQL server succesfully!");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error when connecting to PostgreSQL " +
                    "server!\n" + e.getMessage());
        }
    }

    public void disconnect() {

        try {
            SingletonDBConnection.INSTANCE.getConnection().close();
            System.out.println("Disconnected successfully!");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error when disconnecting from " +
                    "PostgreSQL server!\n" + e.getMessage());
        }
    }

    public void createTables() throws SQLException {

        Statement statement = SingletonDBConnection.INSTANCE.getConnection().createStatement();

        statement.executeUpdate(SQLStatements.CREATE_TABLE_CUSTOMERS.toString());
        statement.executeUpdate(SQLStatements.CREATE_TABLE_CONTACTS.toString());
        statement.close();
    }

    public boolean isConnected() {

        return SingletonDBConnection.INSTANCE.getConnection() != null;
    }
}
