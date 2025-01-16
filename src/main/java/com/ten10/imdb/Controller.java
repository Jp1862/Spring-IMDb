package com.ten10.imdb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@org.springframework.stereotype.Controller
    public class Controller {

        @Autowired
        private FilmTitleRepository repository;

        @GetMapping("/entities")
        public List<FilmTitle> getAllEntities() {
            return (List<FilmTitle>) repository.findAll();
        }
    @GetMapping("/film")
    public String film(@RequestParam(name="name", required=false) String name, Model model) {
        List<FilmTitle> movies = repository.findByPrimaryTitle(name);
        model.addAttribute("movies", movies);
        return "greeting";
    }
    }

