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

        int count = 1;
        int batch = 2000;
        ArrayList<FilmTitle> newBatch = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\jacob.penn\\DEV\\IMDB\\imdb\\title.basics.tsv"));
            String line;
            reader.readLine();
            int startYear;
            int runTimeMinutes;
            int filmBatch = 0;
            while ((line = reader.readLine()) != null) {
                String[] column = line.split("\\t");
                String id = column[0];
                String primaryTitle = column[2];

                try {

                    runTimeMinutes = Integer.parseInt(column[7]);
                    startYear = Integer.parseInt(column[5]);
                } catch (NumberFormatException nfe) {
                    runTimeMinutes = 0;
                    startYear = Integer.MIN_VALUE;
                }
                FilmTitle filmTitle = new FilmTitle(id, primaryTitle, startYear, runTimeMinutes);
                if (runTimeMinutes > 30) {
                    newBatch.add(filmTitle);
                }

                if (count % batch == 0) {
                    repository.saveAll(newBatch);

                    filmBatch += newBatch.size();
                    System.out.println("Added " + filmBatch + " movies");
                    newBatch.clear();
                }
                count++;
            }
            if (!newBatch.isEmpty()) {
                repository.saveAll(newBatch);
                System.out.println("Added final " + newBatch.size() + " movies");
                System.out.println("Total movies added: " + (filmBatch + newBatch.size()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
