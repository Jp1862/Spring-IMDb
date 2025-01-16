package com.ten10.imdb;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FilmTitle {
    @Id
    private String tconst;
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
                "FilmTitle[titleId='%s', title='%s']",
                tconst, primaryTitle);
    }
    public String getTconst() {
        return tconst;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

}
