package com.example.fainl_android.Class;

public class Product_class {

    private int id;
    private int count;
    private String description;
    private double price;
    private int image;
    private String name;

    public Product_class(int id, int count, String description, double price, int image, String name) {
        this.id = id;
        this.count = count;
        this.description = description;
        this.price = price;
        this.image = image;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
