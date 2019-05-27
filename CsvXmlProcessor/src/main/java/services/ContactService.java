package services;

import models.Contact;
import models.ContactType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jakub Filipiak on 21.05.2019.
 */
public class ContactService {

    public List<Contact> sortStringContacts(List<String> unsortedStringContacts) {

        List<Contact> sortedContacts = new ArrayList<>();
        for (String stringContact : unsortedStringContacts) {
            ContactType type = detectContactType(stringContact);
            sortedContacts.add(new Contact(type, stringContact));
        }
        return sortedContacts;
    }

    private ContactType detectContactType(String stringContact) {

        Pattern emailPattern = Pattern.compile(".+@.+...");
        Pattern phonePattern = Pattern.compile("[0-9]{3}\\s?[0-9]{3}\\s?[0-9]{3}");
        Pattern jabberPattern = Pattern.compile("jbr:.+");

        Matcher emailMatcher = emailPattern.matcher(stringContact);
        Matcher phoneMatcher = phonePattern.matcher(stringContact);
        Matcher jabberMatcher = jabberPattern.matcher(stringContact);

        if (emailMatcher.matches()) {
            return ContactType.EMAIL;
        } else if (phoneMatcher.matches()) {
            return ContactType.PHONE;
        } else if (jabberMatcher.matches()) {
            return ContactType.JABBER;
        } else {
            return ContactType.UNKNOWN;
        }
    }

    public List<Contact> createContactsFromMap(Map<ContactType, List<String>> allContactsMap) {

        List<Contact> contacts = new ArrayList<>();

        allContactsMap.keySet().forEach(type -> {
            List<String> currentList = allContactsMap.get(type);
            currentList.forEach(contact -> contacts.add(new Contact(type, contact)));
        });

        return contacts;
    }
}
