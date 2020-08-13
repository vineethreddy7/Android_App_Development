package com.example.voyage;

public class details_place {
    String pname;
    String pprice;
    String plocation;
    int pimage;

    public details_place(String pname, String pprice, String plocation, int pimage) {
        this.pname = pname;
        this.pprice = pprice;
        this.plocation = plocation;
        this.pimage = pimage;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPprice() {
        return pprice;
    }

    public void setPprice(String pprice) {
        this.pprice = pprice;
    }

    public String getPlocation() {
        return plocation;
    }

    public void setPlocation(String plocation) {
        this.plocation = plocation;
    }

    public int getPimage() {
        return pimage;
    }

    public void setPimage(int pimage) {
        this.pimage = pimage;
    }
}
