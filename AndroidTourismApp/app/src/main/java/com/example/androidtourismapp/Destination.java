package com.example.androidtourismapp;

public class Destination {
    private String destinationName;
    private double destinationPrice;
    private String destinationText;
    private int destinationImage;
    private Double lat;
    private Double lon;

    public Destination(String destinationName, double destinationPrice, String destinationText, int destinationImage, Double lat, Double lon) {
        this.destinationName = destinationName;
        this.destinationPrice = destinationPrice;
        this.destinationText = destinationText;
        this.destinationImage = destinationImage;
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public double getDestinationPrice() {

        return destinationPrice;
    }
    public String getDestinationText(){
        return destinationText;
    }


    public int getDestinationImage()
    {
        return destinationImage;
    }
}


