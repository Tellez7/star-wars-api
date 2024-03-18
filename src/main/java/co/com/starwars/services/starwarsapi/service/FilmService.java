package co.com.starwars.services.starwarsapi.service;

import co.com.starwars.services.starwarsapi.model.dto.Film;

public interface FilmService {

    Film save(Film film);

    Film findByIdFilms(String id);

    Film findById(String id);

    Film update(Film film);

    void deleteById(String id);

}
