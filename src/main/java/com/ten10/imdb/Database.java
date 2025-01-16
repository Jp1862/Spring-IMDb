package com.ten10.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class Database {
    @Autowired
    FilmTitleRepository repository;

    public void getBufferedReader() {
        BufferedReader reader;

        int count = 0;
        int batch = 1000;
        ArrayList<FilmTitle> newBatch = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\jacob.penn\\DEV\\IMDB\\imdb\\title.basics.tsv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rows = line.split("\\t");
                String id = rows[0];
                String primaryTitle = rows[2];
                FilmTitle filmTitle = new FilmTitle(id, primaryTitle);

                newBatch.add(filmTitle);
                if (count%batch ==0){
                    repository.saveAll(newBatch);
                    newBatch.clear();
                    System.out.println("Added " + count + "movies");
                }
                count++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
