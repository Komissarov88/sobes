package com.komissarov.sobes.buider;

public class PersonBuilder {

    private Person person;

    public PersonBuilder() {
        person = new Person();
    }

    public Person getPerson() {
        return person;
    }

    public void buildName(String firstName, String lastName, String middleName) {
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setMiddleName(middleName);
    }

    public void buildAddress(String country, String address) {
        person.setCountry(country);
        person.setAddress(address);
    }

    public void buildMisc(String phone, int age, String gender) {
        person.setPhone(phone);
        person.setAge(age);
        person.setGender(gender);
    }
}
