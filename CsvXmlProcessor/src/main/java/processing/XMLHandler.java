package processing;

import models.Contact;
import models.ContactType;
import models.Customer;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import services.ContactService;
import services.CustomerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jakub Filipiak on 18.05.2019.
 */
public class XMLHandler extends DefaultHandler {

    private ContactService contactService = new ContactService();
    private CustomerService customerService = new CustomerService();

    private String tmpValue;
    private Customer customer;

    private List<String> unknownContacts;
    private List<String> emails;
    private List<String> phones;
    private List<String> jabbers;

    private Map<ContactType, List<String>> allContactsMap;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        XMLTag xmlStartTag = XMLTag.fromString(qName);
        switch (xmlStartTag) {
            case PERSON:
                customer = new Customer();
                break;
            case CONTACTS:
                unknownContacts = new ArrayList<>();
                emails = new ArrayList<>();
                phones = new ArrayList<>();
                jabbers = new ArrayList<>();
                allContactsMap = new HashMap<>();
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        tmpValue = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        XMLTag xmlEndTag = XMLTag.fromString(qName);
        switch (xmlEndTag) {
            case NAME:
                customer.setName(tmpValue);
                break;
            case SURNAME:
                customer.setSurname(tmpValue);
                break;
            case AGE:
                String maybeNumber = tmpValue.trim();
                if (!maybeNumber.isEmpty()) {
                    int age = Integer.parseInt(maybeNumber);
                    customer.setAge(age);
                }
                break;
            case CITY:
                break;
            case CONTACTS:
                if (!unknownContacts.isEmpty())
                    allContactsMap.put(ContactType.UNKNOWN, unknownContacts);
                if (!emails.isEmpty())
                    allContactsMap.put(ContactType.EMAIL, emails);
                if (!phones.isEmpty())
                    allContactsMap.put(ContactType.PHONE, phones);
                if (!jabbers.isEmpty())
                    allContactsMap.put(ContactType.JABBER, jabbers);
                break;
            case PHONE:
                phones.add(tmpValue);
                break;
            case EMAIL:
                emails.add(tmpValue);
                break;
            case JABBER:
                jabbers.add(tmpValue);
                break;
            case PERSON:
                List<Contact> contacts =
                        contactService.createContactsFromMap(allContactsMap);
                customer.setContacts(contacts);
                customerService.saveCustomer(customer);
                break;
            case PERSONS:
                break;
            default:
                unknownContacts.add(tmpValue);
                break;
        }
    }
}
