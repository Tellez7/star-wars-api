package co.com.starwars.services.starwarsapi.service.impl;

import static co.com.starwars.services.starwarsapi.util.EntityToObject.entityToResponse;
import static co.com.starwars.services.starwarsapi.util.constants.Constant.MAX_SIZE_ID;
import static java.lang.Integer.parseInt;

import co.com.starwars.services.starwarsapi.dao.FilmDao;
import co.com.starwars.services.starwarsapi.model.dto.Film;
import co.com.starwars.services.starwarsapi.model.entity.FilmEntity;
import co.com.starwars.services.starwarsapi.service.FilmService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class FilmServiceImpl implements FilmService {

    private final FilmDao filmDao;

    @Override
    public Film save(Film film) {
        return entityOrThrowNotFoundException(filmDao.save(film));
    }

    @Override
    public Film findByIdFilms(String id) {
        if (biggerIdOrIsNumber(id)) {
            return save(entityOrThrowNotFoundException(filmDao.findByIdFilms(parseInt(id))));
        }
        throw new NumberFormatException();
    }

    @Override
    public Film findById(String id) {
        if (biggerIdOrIsNumber(id)) {
            return entityOrThrowNotFoundException(filmDao.findById(parseInt(id)));
        }
        throw new NumberFormatException();
    }

    @Override
    public Film update(Film film) {
        if (biggerIdOrIsNumber(String.valueOf(film.getEpisodeId()))) {
            return entityOrThrowNotFoundException(filmDao.update(film));
        }
        throw new NumberFormatException();
    }

    @Override
    public void deleteById(String id) {
        if (!biggerIdOrIsNumber(id)) {
            throw new NumberFormatException();
        } else if (findById(id) != null) {
            filmDao.deleteById(parseInt(id));
        }
    }

    private Film entityOrThrowNotFoundException(FilmEntity response) {
        if (response == null) {
            throw new EntityNotFoundException();
        }
        return entityToResponse(response);
    }

    private boolean biggerIdOrIsNumber(String number) {
        if (number.length() <= MAX_SIZE_ID) {
            try {
                parseInt(number);
            } catch (NumberFormatException numberFormatException) {
                return false;
            }
            return true;
        }
        return false;
    }
}
