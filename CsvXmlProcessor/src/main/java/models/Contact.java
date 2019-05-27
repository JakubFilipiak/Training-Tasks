package models;

/**
 * Created by Jakub Filipiak on 18.05.2019.
 */
public class Contact {

    private long customerId;
    private ContactType type;
    private String contact;

    public Contact(ContactType type, String contact) {
        this.type = type;
        this.contact = contact;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public ContactType getType() {
        return type;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "type=" + type +
                ", contact='" + contact + '\'' +
                '}';
    }
}
