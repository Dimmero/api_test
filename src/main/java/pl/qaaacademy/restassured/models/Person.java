package pl.qaaacademy.restassured.models;

public class Person {
    private String email;
    private String lastname;
    private String phone;
    private String surname;

    public Person(String email, String lastname, String phone, String surname) {
        this.email = email;
        this.lastname = lastname;
        this.phone = phone;
        this.surname = surname;
    }

    public Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
