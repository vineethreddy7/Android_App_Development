package com.example.voyage;

public class Booking {
    private long id;
    private String date;
    private long offeringid;
    private String hostemail;
    private String travelleremail;

    Booking(String date, long offeringid, String hostemail, String travelleremail) {
        this.date = date;
        this.offeringid = offeringid;
        this.hostemail = hostemail;
        this.travelleremail = travelleremail;
    }

    Booking(long id, String date, long offeringid, String hostemail, String travelleremail) {
        this.id = id;
        this.date = date;
        this.offeringid = offeringid;
        this.hostemail = hostemail;
        this.travelleremail = travelleremail;
    }

    public Booking() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getOfferingid() {
        return offeringid;
    }

    public void setOfferingid(long offeringid) {
        this.offeringid = offeringid;
    }

    public String getHostemail() {
        return hostemail;
    }

    public void setHostemail(String hostemail) {
        this.hostemail = hostemail;
    }

    public String getTravelleremail() {
        return travelleremail;
    }

    public void setTravelleremail(String travelleremail) {
        this.travelleremail = travelleremail;
    }



}
