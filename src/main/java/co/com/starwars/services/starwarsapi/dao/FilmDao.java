package co.com.starwars.services.starwarsapi.dao;

import co.com.starwars.services.starwarsapi.model.dto.Film;
import co.com.starwars.services.starwarsapi.model.entity.FilmEntity;

// DAO
public interface FilmDao {

    FilmEntity findByIdFilms(Integer id);

    FilmEntity findById(Integer id);

    FilmEntity save(Film film);

    FilmEntity update(Film film);

    void deleteById(Integer id);

}
