package fr.unice.polytech.si3.gregorymerlet.enseigne.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class Product implements Serializable{

    private String name;
    private String type;
    private String description;
    private double price;
    private int imageId;

    public Product(String name, String type, String description, double price, int imageId){
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getImageId() {
        return imageId;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        this.name = inputStream.readUTF();
        this.type = inputStream.readUTF();
        this.description = inputStream.readUTF();
        this.price = inputStream.readDouble();
        this.imageId = inputStream.readInt();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeUTF(this.name);
        outputStream.writeUTF(this.type);
        outputStream.writeUTF(this.description);
        outputStream.writeDouble(this.price);
        outputStream.writeInt(this.imageId);
    }
}
