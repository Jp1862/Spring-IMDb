package com.ten10.imdb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private FilmTitleRepository repository;

    @GetMapping("/home")
    public String firstPage() {
        return "home";
    }

    @GetMapping("/byPrimaryTitle")
    public String getFilm(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "year") String year,
            Model model) {
        logger.info("Searching for " + title);
        List<FilmTitle> filmTitles = repository.findByPrimaryTitleContainingIgnoreCase(title);
        List<FilmTitle> finalFilms = new ArrayList<>();
        separateByYear(year, filmTitles, finalFilms);
        finalFilms.sort(Comparator.comparingDouble(FilmTitle::getRating).reversed());
        model.addAttribute("finalFilms", finalFilms);
        return "searchResult";
    }

    private static void separateByYear(String year, List<FilmTitle> filmTitles, List<FilmTitle> finalFilms) {
        for (FilmTitle film : filmTitles) {
            if (film.getStartYear() < 1950 && year.equals("Pre-1950")) {
                finalFilms.add(film);
            } else if (film.getStartYear() >= 1950 && film.getStartYear() < 1980 && year.equals("1950-1979")) {
                finalFilms.add(film);
            } else if (film.getStartYear() >= 1980 && film.getStartYear() < 2010 && year.equals("1980-2009")) {
                finalFilms.add(film);
            } else if (film.getStartYear() >= 2010 && year.equals("2010-Present")) {
                finalFilms.add(film);
            } else if (year.equals("all")){
                finalFilms.add(film);
            }
        }
    }

}



