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

    protected FilmTitle() {
    }

    public FilmTitle(String tconst, String primaryTitle, int startYear, int runTimeMinutes) {
        this.tconst = tconst;
        this.primaryTitle = primaryTitle;
        this.startYear = startYear;
        this.runTimeMinutes = runTimeMinutes;
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
}
