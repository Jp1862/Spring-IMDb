package com.ten10.imdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@SpringBootApplication
public class ImdbApplication {

    private static final Logger log = LoggerFactory.getLogger(ImdbApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(ImdbApplication.class, args);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\jacob.penn\\DEV\\IMDB\\imdb\\title.basics.tsv"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(readAllLinesWithStream(reader,50));
    }
    public static String readAllLinesWithStream (BufferedReader reader, int limit){
            return reader.lines().limit(limit)
                    .collect(Collectors.joining(System.lineSeparator()));
        }

}


