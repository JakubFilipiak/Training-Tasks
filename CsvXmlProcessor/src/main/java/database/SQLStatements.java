package database;

/**
 * Created by Jakub Filipiak on 26.05.2019.
 */
public enum SQLStatements {

    CREATE_TABLE_CUSTOMERS("CREATE TABLE IF NOT EXISTS Customers"
            + "(id SERIAL PRIMARY KEY,"
            + "name VARCHAR (50) NOT NULL,"
            + "surname VARCHAR (50) NOT NULL,"
            + "age SMALLINT);"),
    CREATE_TABLE_CONTACTS("CREATE TABLE IF NOT EXISTS Contacts"
            + "(id SERIAL,"
            + "id_customer INTEGER REFERENCES CUSTOMERS(id),"
            + "type SMALLINT NOT NULL,"
            + "contact VARCHAR (255) NOT NULL,"
            + "PRIMARY KEY (ID,ID_CUSTOMER))"),
    INSERT_INTO_CUSTOMERS("INSERT INTO Customers(name,surname,age) "
            + "VALUES(?,?,?)"),
    INSERT_INTO_CUSTOMERS_WITHOUT_AGE("INSERT INTO Customers(name,surname) "
            + "VALUES(?,?)"),
    INSERT_INTO_CONTACTS("INSERT INTO Contacts(id_customer,type,contact) "
            + "VALUES(?,?,?)");

    private String sql;

    SQLStatements(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return sql;
    }
}
