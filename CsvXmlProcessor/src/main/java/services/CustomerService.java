package services;

import models.Contact;
import models.Customer;
import repositories.ContactRepository;
import repositories.CustomerRepository;

/**
 * Created by Jakub Filipiak on 21.05.2019.
 */
public class CustomerService {

    private CustomerRepository customerRepository = new CustomerRepository();
    private ContactRepository contactRepository = new ContactRepository();

    public void saveCustomer(Customer customer) {
        System.out.println(customer);

        long customerId;
        if (customer.hasNoAge()) {
            customerId = customerRepository.insertCustomerWithoutAge(customer);
        } else {
            customerId = customerRepository.insertCustomer(customer);
        }

        if (customerId != -1)
        for (Contact contact : customer.getContacts()) {
            contact.setCustomerId(customerId);
            contactRepository.insertContact(contact);
        }
    }
}
