package com.example.voyage;

public class Offering {
    private long id;
    private String name;
    private String photo;
    private Double price;
    private String place;
    private Double latitude;
    private Double longitude;
    private Double rating;
    private String description;
    private String hostemail;
    private String review;
    private String type;



    private String offer;

    Offering(long id, String name, String photo, Double price, String place, Double latitude, Double longitude, Double rating, String description, String hostemail, String review, String type,String offer) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.description = description;
        this.hostemail = hostemail;
        this.review = review;
        this.type = type;
        this.offer = offer;
    }

    Offering(String name, String photo, Double price, String place, Double latitude, Double longitude, Double rating, String description, String hostemail, String review, String type,String offer) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.description = description;
        this.hostemail = hostemail;
        this.review = review;
        this.type = type;
        this.offer = offer;
    }

    Offering(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostemail() {
        return hostemail;
    }

    public void setHostemail(String hostemail) {
        this.hostemail = hostemail;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }
}
