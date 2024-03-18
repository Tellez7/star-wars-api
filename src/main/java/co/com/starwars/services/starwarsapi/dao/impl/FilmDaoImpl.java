package co.com.starwars.services.starwarsapi.dao.impl;

import static co.com.starwars.services.starwarsapi.util.EntityToObject.consumeResponseToEntity;
import static co.com.starwars.services.starwarsapi.util.EntityToObject.filmToEntity;

import co.com.starwars.services.starwarsapi.consumer.FilmConsumer;
import co.com.starwars.services.starwarsapi.dao.FilmDao;
import co.com.starwars.services.starwarsapi.model.dto.Film;
import co.com.starwars.services.starwarsapi.model.entity.FilmEntity;
import co.com.starwars.services.starwarsapi.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FilmDaoImpl implements FilmDao {

    private final FilmConsumer filmConsumer;

    private final FilmRepository filmRepository;

    @Override
    public FilmEntity findByIdFilms(Integer id) {
        return consumeResponseToEntity(filmConsumer.consumer(id));
    }

    @Override
    public FilmEntity findById(Integer id) {
        return filmRepository.findById(id).orElse(null);
    }

    @Override
    public FilmEntity save(Film film) {
        return filmRepository.save(filmToEntity(film));
    }

    @Override
    public FilmEntity update(Film film) {
        return filmRepository.existsById(film.getEpisodeId()) ? save(film) : null;
    }

    @Override
    public void deleteById(Integer id) {
        filmRepository.deleteById(id);
    }
}
