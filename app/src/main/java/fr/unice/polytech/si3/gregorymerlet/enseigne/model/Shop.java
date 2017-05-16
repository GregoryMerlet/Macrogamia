package fr.unice.polytech.si3.gregorymerlet.enseigne.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shop implements Serializable{

    private String name;
    private LatLng latLng;
    private String adress;
    private List<String> openHours;
    private List<Product> products;

    public Shop(String name, LatLng latLng, String adress){
        this.name = name;
        this.latLng = latLng;
        this.adress = adress;
        this.openHours = new ArrayList<>();
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getAdress() {
        return adress;
    }

    public List<String> getOpenHours() {
        return openHours.subList(0, 7);
    }

    public void addOpenHour(int day, String openHour){
        this.openHours.add(day, openHour);
    }

    public void addProduct(Product product){
        if(!products.contains(product))
            products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString(){
        return this.name;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        this.name = inputStream.readUTF();
        double latitude = inputStream.readDouble();
        double longitude = inputStream.readDouble();
        this.latLng = new LatLng(latitude, longitude);
        this.adress = inputStream.readUTF();
        this.openHours = (List<String>) inputStream.readObject();
        this.products = (List<Product>) inputStream.readObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeUTF(this.name);
        outputStream.writeDouble(this.latLng.latitude);
        outputStream.writeDouble(this.latLng.longitude);
        outputStream.writeUTF(adress);
        outputStream.writeObject(openHours);
        outputStream.writeObject(this.products);
    }
}
