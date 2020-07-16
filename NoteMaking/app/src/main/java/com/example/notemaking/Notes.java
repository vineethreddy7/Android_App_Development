package com.example.notemaking;

public class Notes {
    String subject;
    String name;
    String description;
    String date;
    String time;
    int id;


    public Notes(String subject, String name, String description, String date, String time, int id){
        this.id = id;
        this.subject = subject;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public int getId(){ return id; }

    public String getSubject(){ return subject; }

    public String getName(){ return name; }

    public String getDescription() { return description; }

    public String getDate(){ return date; }

    public String getTime(){ return time; }
}
