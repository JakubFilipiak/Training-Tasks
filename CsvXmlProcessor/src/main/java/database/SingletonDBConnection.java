package database;

import java.sql.Connection;

/**
 * Created by Jakub Filipiak on 27.05.2019.
 */
public enum SingletonDBConnection {

    INSTANCE;

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
