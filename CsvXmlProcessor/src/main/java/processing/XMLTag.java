package processing;

/**
 * Created by Jakub Filipiak on 21.05.2019.
 */
public enum XMLTag {

    PERSONS("persons"),
    PERSON("person"),
    NAME("name"),
    SURNAME("surname"),
    AGE("age"),
    CITY("city"),
    CONTACTS("contacts"),
    EMAIL("email"),
    PHONE("phone"),
    JABBER("jabber"),
    UNKNOWN("unknown");

    private String tag;

    XMLTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return tag;
    }

    public static XMLTag fromString(String stringTag) {
        for (XMLTag xmlTag : XMLTag.values()) {
            if (xmlTag.toString().equals(stringTag)) {
                return xmlTag;
            }
        }
        return UNKNOWN;
    }
}
