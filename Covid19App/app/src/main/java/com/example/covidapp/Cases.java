package com.example.covidapp;

public class Cases {
    String continent;
    String country;
    int allCases;
    int death;
    int activeCases;
    int recovered;

    public Cases(String continent, String country, int allCases, int death, int activeCases, int recovered) {
        this.continent = continent;
        this.country = country;
        this.allCases = allCases;
        this.death = death;
        this.activeCases = activeCases;
        this.recovered = recovered;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public int getAllCases() {
        return allCases;
    }

    public int getDeath() {
        return death;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public int getRecovered() {
        return recovered;
    }
}
