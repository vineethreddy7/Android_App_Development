package com.example.testapp1;

public class products {
    private String brand;
    private String name;
    private double price;
    private int image;

    public products(String brand, String name, double price, int image) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }



    public int getImage() {
        return image;
    }
}
