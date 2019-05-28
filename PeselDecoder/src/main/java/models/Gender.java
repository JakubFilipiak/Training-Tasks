package models;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public enum Gender {

    FEMALE(true),
    MALE(false);

    private boolean parity;

    Gender(boolean parity) {
        this.parity = parity;
    }

    public static Gender fromParity(boolean parity) {

        for (Gender gender : Gender.values()) {
            if (parity == gender.parity)
                return gender;
        }
        return null;
    }
}
