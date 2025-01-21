package com.ten10.imdb;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FilmTitle {
    @Id
    private String tconst;

    @Column(nullable = false, length = 500)
    private String primaryTitle;
    private int startYear;
    private int runTimeMinutes;
    private double rating;

    protected FilmTitle() {
    }

    public FilmTitle(String tconst, String primaryTitle, int startYear, int runTimeMinutes, double rating) {
        this.tconst = tconst;
        this.primaryTitle = primaryTitle;
        this.startYear = startYear;
        this.runTimeMinutes = runTimeMinutes;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s",
                tconst, primaryTitle);
    }

    public String getTconst() {
        return tconst.substring(2);
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public double getRating() {
        return rating;
    }
}
