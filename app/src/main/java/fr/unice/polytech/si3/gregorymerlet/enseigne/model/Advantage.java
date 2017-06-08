package fr.unice.polytech.si3.gregorymerlet.enseigne.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Advantage implements Serializable {

    private String text;
    private int cost;

    public Advantage(String text, int cost){
        this.text = text;
        this.cost = cost;
    }

    public String getText() {
        return text;
    }

    public int getCost() {
        return cost;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        this.text = inputStream.readUTF();
        this.cost = inputStream.readInt();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeUTF(this.text);
        outputStream.writeInt(this.cost);
    }
}
