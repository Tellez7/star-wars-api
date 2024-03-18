package co.com.starwars.services.starwarsapi.service.impl;

import static co.com.starwars.services.starwarsapi.util.EntityToObject.entityToResponse;
import static java.lang.String.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.com.starwars.services.starwarsapi.dao.impl.FilmDaoImpl;
import co.com.starwars.services.starwarsapi.model.dto.Film;
import co.com.starwars.services.starwarsapi.model.entity.FilmEntity;
import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FilmServiceImplTest {

    private FilmServiceImpl filmService;
    private FilmEntity filmEntity;
    private String numberOrNot;
    @Mock
    private FilmDaoImpl filmDao;

    @BeforeEach
    void setUp() {
        filmEntity = FilmEntity.builder()
                .episodeId(1)
                .title("A New Hope")
                .releaseDate(new Date())
                .build();

        filmService = new FilmServiceImpl(filmDao);
    }

    @Test
    void findByIdFilmsWhenIdIsNumber() {
        when(filmDao.findByIdFilms(anyInt())).thenReturn(filmEntity);

        when(filmDao.save(any(Film.class))).thenReturn(filmEntity);

        Film filmResponse = filmService.findByIdFilms(valueOf(filmEntity.getEpisodeId()));

        assertNotNull(filmResponse);
        assertThat(filmResponse, samePropertyValuesAs(entityToResponse(filmEntity)));
    }

    @Test
    void findByIdFilmsWhenIdIsNumberAndBiggerThanMaxSize() {
        numberOrNot = "111111";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                filmService.findByIdFilms(numberOrNot));

        assertNotNull(exception);
    }

    @Test
    void findByIdFilmsWhenIdIsNotNumberAndSmallerThanMaxSize() {
        numberOrNot = "happy";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                filmService.findByIdFilms(numberOrNot));

        assertNotNull(exception);
    }

    @Test
    void findByIdWhenIdIsNumber() {
        when(filmDao.findById(anyInt())).thenReturn(filmEntity);

        Film filmResponse = filmService.findById(valueOf(filmEntity.getEpisodeId()));

        assertNotNull(filmResponse);
        assertThat(filmResponse, samePropertyValuesAs(entityToResponse(filmEntity)));
    }

    @Test
    void findByIdWhenIdIsNumberAndBiggerThanMaxSize() {
        numberOrNot = "222222";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                filmService.findById(numberOrNot));

        assertNotNull(exception);
    }

    @Test
    void findByIdWhenIdIsNotNumberAndSmallerThanMaxSize() {

        when(filmDao.findById(anyInt())).thenReturn(null);

        numberOrNot = "7";

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                filmService.findById(numberOrNot));

        assertNotNull(exception);
    }

    @Test
    void findByIdWhenIsNull() {
        numberOrNot = "happy";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                filmService.findById(numberOrNot));

        assertNotNull(exception);
    }

    @Test
    void updateWhenIdIsNumber() {
        when(filmDao.update(any(Film.class))).thenReturn(filmEntity);

        Film filmResponse = filmService.update(entityToResponse(filmEntity));

        assertNotNull(filmResponse);
        assertThat(filmResponse, samePropertyValuesAs(entityToResponse(filmEntity)));
    }

    @Test
    void updateWhenIdIsNumberAndBiggerThanMaxSize() {
        numberOrNot = "333333";
        filmEntity.setEpisodeId(Integer.parseInt(numberOrNot));

        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                filmService.update(entityToResponse(filmEntity)));

        assertNotNull(exception);
    }

    @Test
    void deleteWhenIdIsNumber() {
        numberOrNot = "1";
        doNothing().when(filmDao).deleteById(anyInt());

        when(filmDao.findById(anyInt())).thenReturn(filmEntity);

        filmService.deleteById(numberOrNot);

        verify(filmDao, times(1)).deleteById(Integer.parseInt(numberOrNot));
    }

    @Test
    void deleteWhenIdIsNumberAndBiggerThanMaxSize() {
        numberOrNot = "444444";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                filmService.deleteById(numberOrNot));

        assertNotNull(exception);
    }

    @Test
    void deleteWhenIdIsNotNumberAndSmallerThanMaxSize() {
        numberOrNot = "happy";

        NumberFormatException exception = assertThrows(NumberFormatException.class, () ->
                filmService.deleteById(numberOrNot));

        assertNotNull(exception);
    }
}