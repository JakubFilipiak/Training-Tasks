package repositories;

import database.SQLStatements;
import models.Contact;
import database.SingletonDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jakub Filipiak on 27.05.2019.
 */
public class ContactRepository {

    private static final Logger LOGGER =
            Logger.getLogger(ContactRepository.class.getName());

    public void insertContact(Contact contact) {

        String SQL = SQLStatements.INSERT_INTO_CONTACTS.toString();
        Connection connection = SingletonDBConnection.INSTANCE.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setLong(1, contact.getCustomerId());
            preparedStatement.setInt(2, contact.getType().toInt());
            preparedStatement.setString(3, contact.getContact());

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error! " + e.getMessage());
        }
    }
}
