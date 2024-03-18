package co.com.starwars.services.starwarsapi.dao.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import co.com.starwars.services.starwarsapi.consumer.FilmConsumer;
import co.com.starwars.services.starwarsapi.consumer.model.FilmConsumerResponse;
import co.com.starwars.services.starwarsapi.model.dto.Film;
import co.com.starwars.services.starwarsapi.model.entity.FilmEntity;
import co.com.starwars.services.starwarsapi.repository.FilmRepository;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FilmDaoImplTest {

    private FilmDaoImpl filmDao;
    private FilmEntity filmEntity;
    private FilmConsumerResponse filmConsumerResponse;
    private Film film;

    @Mock
    private FilmConsumer filmConsumer;

    @Mock
    private FilmRepository filmRepository;

    @BeforeEach
    void setUp() {
        filmEntity = FilmEntity.builder()
                .episodeId(1)
                .title("A New Hope")
                .releaseDate(new Date())
                .build();

        filmConsumerResponse = FilmConsumerResponse.builder()
                .episodeId(3)
                .title("Revenge of the Sith")
                .releaseDate(new Date())
                .build();

        film = Film.builder()
                .episodeId(1)
                .title("A New Hope")
                .releaseDate(new Date())
                .build();

        filmDao = new FilmDaoImpl(filmConsumer, filmRepository);
    }

    @Test
    void findByIdFilms() {
        when(filmConsumer.consumer(anyInt())).thenReturn(filmConsumerResponse);

        FilmEntity filmResponse = filmDao.findByIdFilms(filmConsumerResponse.getEpisodeId());

        assertNotNull(filmResponse);
        assertEquals(filmResponse.getEpisodeId(), filmConsumerResponse.getEpisodeId());
    }

    @Test
    void findById() {
        when(filmRepository.findById(anyInt())).thenReturn(Optional.of(filmEntity));

        FilmEntity filmResponse = filmDao.findById(film.getEpisodeId());

        assertNotNull(filmResponse);
        assertThat(filmResponse, samePropertyValuesAs(filmEntity));
    }

    @Test
    void update() {
        when(filmRepository.existsById(anyInt())).thenReturn(true);

        when(filmRepository.save(any(FilmEntity.class))).thenReturn(filmEntity);

        FilmEntity filmResponse = filmDao.update(film);

        assertNotNull(filmResponse);
        assertThat(filmResponse, samePropertyValuesAs(filmEntity));
    }

    @Test
    void deleteById() {
        doNothing().when(filmRepository).deleteById(anyInt());

        filmDao.deleteById(film.getEpisodeId());

        verify(filmRepository, times(1)).deleteById((filmEntity.getEpisodeId()));
    }
}