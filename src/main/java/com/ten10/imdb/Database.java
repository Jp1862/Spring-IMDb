package com.ten10.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Database {
    @Autowired
    private FilmTitleRepository repository;

    public void getFiles() {
        String tsvRatings = "C:\\Users\\jacob.penn\\DEV\\IMDB\\imdb\\title.ratings.tsv";
        Map<String, Double> ratingsMap = getRatings(tsvRatings);
        String tsvTitle = "C:\\Users\\jacob.penn\\DEV\\IMDB\\imdb\\title.basics.tsv";
        getTitles(tsvTitle, ratingsMap);
    }

    private Map<String, Double> getRatings(String filePath) {
        Map<String, Double> ratingsMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split("\\t");
                try {
                    ratingsMap.put(columns[0], Double.parseDouble(columns[1]));
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ratingsMap;
    }

    private void getTitles(String filePath, Map<String, Double> ratingsMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<FilmTitle> filmBatch = new ArrayList<>();
            reader.readLine();

            String line;
            int count = 0;
            int totalFilms = 0;

            while ((line = reader.readLine()) != null) {
                FilmTitle film = parseLine(line, ratingsMap);
                if (film != null && film.getRunTimeMinutes() > 30 && film.getStartYear() > 0 && film.getRating() > 0) {
                    filmBatch.add(film);
                }
                count++;
                int batch = 2000;
                if (count % batch == 0) {
                    totalFilms += filmBatch.size();
                    repository.saveAll(filmBatch);
                    System.out.println("Added " + totalFilms + " films");
                    filmBatch.clear();
                }
            }

            if (!filmBatch.isEmpty()) {
                repository.saveAll(filmBatch);
                System.out.println("Added final " + filmBatch.size() + " films");
                System.out.println("Total films added: " + (totalFilms + filmBatch.size()));
                filmBatch.clear();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private FilmTitle parseLine(String line, Map<String, Double> ratingsMap) {
        String[] columns = line.split("\\t");
        if (columns.length < 8) return null;

        String id = columns[0];
        String primaryTitle = columns[2];
        int startYear = parseInteger(columns[5]);
        int runTimeMinutes = parseInteger(columns[7]);
        double rating = ratingsMap.getOrDefault(id, 0.0);

        return new FilmTitle(id, primaryTitle, startYear, runTimeMinutes, rating);
    }

    private int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

}
