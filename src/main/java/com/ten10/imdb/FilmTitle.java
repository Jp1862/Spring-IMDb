package com.ten10.imdb;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FilmTitle {
    @Id
    private String tconst;

    @Column (nullable = false, length = 500)
    private String primaryTitle;

    protected FilmTitle(){

    }

    public FilmTitle(String tconst, String primaryTitle){
        this.tconst = tconst;
        this.primaryTitle = primaryTitle;
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

}
