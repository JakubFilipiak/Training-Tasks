package models;

import java.util.List;

/**
 * Created by Jakub Filipiak on 18.05.2019.
 */
public class Customer {

    private String name;
    private String surname;
    private int age = -1;
    private List<Contact> contacts;

    public boolean hasNoAge() {
        return age == -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", contacts=" + contacts +
                '}';
    }
}
