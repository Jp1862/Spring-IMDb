package com.ten10.imdb;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            Model model) {
        logger.info("Searching for " + title);
        List<FilmTitle> filmTitles = repository.findByPrimaryTitleContainingIgnoreCase(title);
        model.addAttribute("filmTitles", filmTitles);
        return "searchResult";
    }

}



