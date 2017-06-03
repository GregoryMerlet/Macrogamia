package fr.unice.polytech.si3.gregorymerlet.enseigne.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private int fideltyPoints;

    public User(String firstName, String lastName, String mail, String password, int fideltyPoints){
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.fideltyPoints = fideltyPoints;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public int getFideltyPoints() {
        return fideltyPoints;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        this.firstName = inputStream.readUTF();
        this.lastName = inputStream.readUTF();
        this.mail = inputStream.readUTF();
        this.password = inputStream.readUTF();
        this.fideltyPoints = inputStream.readInt();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeUTF(this.firstName);
        outputStream.writeUTF(this.lastName);
        outputStream.writeUTF(this.mail);
        outputStream.writeUTF(this.password);
        outputStream.writeInt(this.fideltyPoints);
    }
}
