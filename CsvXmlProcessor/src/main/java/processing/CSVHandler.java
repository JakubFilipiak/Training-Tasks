package processing;

import models.Contact;
import models.Customer;
import services.ContactService;
import services.CustomerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub Filipiak on 18.05.2019.
 */
public class CSVHandler {

    private ContactService contactService = new ContactService();
    private CustomerService customerService = new CustomerService();

    public void parse(BufferedReader bufferedReader) throws IOException {

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (!line.isEmpty()) {
                Customer customer = processLine(line);
                customerService.saveCustomer(customer);
            }
        }
    }

    private Customer processLine(String line) {

        final int NAME_INDEX = 0;
        final int SURNAME_INDEX = 1;
        final int AGE_INDEX = 2;
        final int CITY_INDEX = 3;

        Customer customer = new Customer();
        List<String> unsortedStringContacts = new ArrayList<>();

        String fields[] = line.split(",");
        int fieldIndex = 0;

        for (String field : fields) {
            switch (fieldIndex) {
                case NAME_INDEX:
                    customer.setName(field);
                    break;
                case SURNAME_INDEX:
                    customer.setSurname(field);
                    break;
                case AGE_INDEX:
                    if (!field.isEmpty())
                        customer.setAge(Integer.parseInt(field));
                    break;
                case CITY_INDEX:

                    break;
                default:
                    unsortedStringContacts.add(field);
                    break;
            }
            fieldIndex++;
        }
        List<Contact> sortedContacts = contactService.sortStringContacts(unsortedStringContacts);
        customer.setContacts(sortedContacts);
        return customer;
    }
}
