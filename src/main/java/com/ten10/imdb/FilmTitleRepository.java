package com.ten10.imdb;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmTitleRepository extends CrudRepository<FilmTitle, String> {

    List<FilmTitle> findByPrimaryTitle(String primaryTitle);

}
