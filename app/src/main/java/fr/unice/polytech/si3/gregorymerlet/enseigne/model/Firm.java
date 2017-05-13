package fr.unice.polytech.si3.gregorymerlet.enseigne.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Firm implements Serializable{

    private String name;
    private List<Shop> shops;

    public Firm(){
        this.shops = new ArrayList<>();
    }

    public void init(){
        this.name = "FirmName";

        //Creating shops
        Shop polytech = new Shop("Polytech' Nice Sophia", new LatLng(43.615660, 7.071876));
        Shop paris = new Shop("Test Paris", new LatLng(48.8589507, 2.2775171));

        //Creating products
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1", "type1", "description", 5.0, "/path"));
        products.add(new Product("Product 2", "type2", "description", 5.0, "/path"));
        products.add(new Product("Product 3", "type1", "description", 5.0, "/path"));
        products.add(new Product("Product 4", "type2", "description", 5.0, "/path"));
        products.add(new Product("Product 5", "type1", "description", 5.0, "/path"));
        products.add(new Product("Product 6", "type2", "description", 5.0, "/path"));
        products.add(new Product("Product 7", "type1", "description", 5.0, "/path"));
        products.add(new Product("Product 8", "type2", "description", 5.0, "/path"));
        products.add(new Product("Product 9", "type1", "description", 5.0, "/path"));
        products.add(new Product("Product 10", "type2", "description", 5.0, "/path"));

        //Adding products to shops
        polytech.addProduct(products.get(0));
        polytech.addProduct(products.get(1));
        polytech.addProduct(products.get(2));
        polytech.addProduct(products.get(3));
        polytech.addProduct(products.get(4));
        polytech.addProduct(products.get(5));
        polytech.addProduct(products.get(9));
        paris.addProduct(products.get(0));
        paris.addProduct(products.get(1));
        paris.addProduct(products.get(2));
        paris.addProduct(products.get(5));
        paris.addProduct(products.get(6));
        paris.addProduct(products.get(7));
        paris.addProduct(products.get(8));

        //Adding shops to firm
        addShop(polytech);
        addShop(paris);
    }

    public void addShop(Shop shop){
        if(!shops.contains(shop))
            this.shops.add(shop);
    }

    public List<Shop> getShops(){
        return this.shops;
    }

    public List<Product> getProducts(String type, String sortBy, boolean ascending){
        List<Product> products = new ArrayList<>();
        for(Shop shop : shops)
            for (Product product : shop.getProducts())
                if (!products.contains(product))
                    if(type.equals("all") || product.getType().equals(type))
                        products.add(product);
        products = sortProductsBy(products, sortBy, ascending);
        return products;
    }

    public List<String> getTypes(){
        List<String> types = new ArrayList<>();
        for(Shop shop : shops)
            for(Product product : shop.getProducts())
                if(!types.contains(product.getType()))
                    types.add(product.getType());
        return types;
    }

    public String getName() {
        return name;
    }

    private List<Product> sortProductsBy(List<Product> products, String attribute, boolean ascending){
        List<Product> result = new ArrayList<>();
        switch(attribute){
            case "name":
                return sortProductsByName(products, ascending);
            default:
                return sortProductsByName(products, ascending);
        }
    }

    private List<Product> sortProductsByName(List<Product> products, boolean ascending){
        List<Product> result = new ArrayList<>();
        for(Product product : products){
            if(result.isEmpty()) {
                result.add(product);
            } else {
                for (int i = 0; i < result.size(); i++) {
                    if(ascending) {
                        if (result.get(i).getName().compareTo(product.getName()) > 0) {
                            result.add(i, product);
                            break;
                        } else if (i == result.size() - 1) {
                            result.add(product);
                            break;
                        }
                    } else {
                        if (result.get(i).getName().compareTo(product.getName()) < 0) {
                            result.add(i, product);
                            break;
                        } else if (i == result.size() - 1) {
                            result.add(product);
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    private void readObject(ObjectInputStream inputStream) throws ClassNotFoundException, IOException {
        this.name = inputStream.readUTF();
        this.shops = (List<Shop>) inputStream.readObject();
    }

    private void writeObject(ObjectOutputStream outputStream) throws IOException {
        outputStream.writeUTF(this.name);
        outputStream.writeObject(this.shops);
    }
}
