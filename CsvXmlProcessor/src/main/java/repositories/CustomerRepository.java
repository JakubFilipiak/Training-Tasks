package repositories;

import database.SQLStatements;
import models.Customer;
import database.SingletonDBConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jakub Filipiak on 25.05.2019.
 */
public class CustomerRepository {

    private static final Logger LOGGER =
            Logger.getLogger(CustomerRepository.class.getName());

    public long insertCustomer(Customer customer) {

        String SQL = SQLStatements.INSERT_INTO_CUSTOMERS.toString();
        Connection connection = SingletonDBConnection.INSTANCE.getConnection();
        long customerId = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());
            preparedStatement.setInt(3, customer.getAge());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    customerId = resultSet.getLong(1);
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error! " + e.getMessage());
        }

        return customerId;
    }

    public long insertCustomerWithoutAge(Customer customer) {

        String SQL = SQLStatements.INSERT_INTO_CUSTOMERS_WITHOUT_AGE.toString();
        Connection connection = SingletonDBConnection.INSTANCE.getConnection();
        long customerId = -1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSurname());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    customerId = resultSet.getLong(1);
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error! " + e.getMessage());
        }

        return customerId;
    }
}
