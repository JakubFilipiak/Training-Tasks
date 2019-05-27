package models;

/**
 * Created by Jakub Filipiak on 18.05.2019.
 */
public enum ContactType {

    UNKNOWN(0), EMAIL(1), PHONE(2), JABBER(3);

    private int type;

    ContactType(int type) {
        this.type = type;
    }

    public int toInt() {
        return type;
    }
}
