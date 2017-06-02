package fr.unice.polytech.si3.gregorymerlet.enseigne.model;

public class User {

    String firstName;
    String lastName;
    String mail;
    String password;

    public User(String firstName, String lastName, String mail, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
