package com.ten10.imdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
@Controller
@SpringBootApplication
public class ImdbApplication {

    @Autowired
    FilmTitleRepository repository;
    private static final Logger log = LoggerFactory.getLogger(ImdbApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ImdbApplication.class, args);
    }

}



