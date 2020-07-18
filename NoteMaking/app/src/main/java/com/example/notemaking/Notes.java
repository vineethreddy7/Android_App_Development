package com.example.notemaking;

public class Notes {
    private String subject;
    private String name;
    private String description;
    private Double latitude;

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

    private Double longitude;

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    private String audio;




    private String date;
    private String time;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;


    private long id;


    Notes() {

    }

    Notes(long id,String subject, String name, String description, String date, String time, String img,String audio, Double latitude,Double longitude){
        this.id = id;
        this.subject = subject;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.img = img;
        this.audio = audio;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }


    public String getSubject(){ return subject; }

    public String getName(){ return name; }

    public String getDescription() { return description; }

    public String getDate(){ return date; }

    public String getTime(){ return time; }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(long id) {
        this.id = id;
    }

    Notes(String subject, String name, String description, String date, String time, String img, String audio, Double latitude,Double longitude) {
        this.subject = subject;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.img = img;
        this.audio = audio;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
