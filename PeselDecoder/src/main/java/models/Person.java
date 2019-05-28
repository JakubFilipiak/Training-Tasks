package models;

import java.time.LocalDate;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public class Person {

    private String pesel;
    private LocalDate dateOfBirth;
    private Gender gender;

    private Person(PersonBuilder personBuilder) {
        this.pesel = personBuilder.pesel;
        this.dateOfBirth = personBuilder.dateOfBirth;
        this.gender = personBuilder.gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pesel='" + pesel + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }

    public static class PersonBuilder {

        private String pesel;
        private LocalDate dateOfBirth;
        private Gender gender;

        public PersonBuilder setPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public PersonBuilder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonBuilder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
