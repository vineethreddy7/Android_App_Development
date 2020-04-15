package com.example.foodapp;

public class Food {
    private String name;
    private double price;
    private String imgName;
    private String cusinine;
    private Boolean spicy;

    public Food(String name, double price, String imgName, String cusinine, Boolean spicy) {
        this.name = name;
        this.price = price;
        this.imgName = imgName;
        this.cusinine = cusinine;
        this.spicy = spicy;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImgName() {
        return imgName;
    }

    public String getCusinine() {
        return cusinine;
    }

    public Boolean getSpicy() {
        return spicy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setCusinine(String cusinine) {
        this.cusinine = cusinine;
    }

    public void setSpicy(Boolean spicy) {
        this.spicy = spicy;
    }
}
