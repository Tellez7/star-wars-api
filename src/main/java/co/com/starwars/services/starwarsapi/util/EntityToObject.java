package co.com.starwars.services.starwarsapi.util;

import co.com.starwars.services.starwarsapi.consumer.model.FilmConsumerResponse;
import co.com.starwars.services.starwarsapi.model.dto.Film;
import co.com.starwars.services.starwarsapi.model.entity.FilmEntity;

public class EntityToObject {

    public static Film entityToResponse(FilmEntity filmEntity) {
        return Film.builder()
                .episodeId(filmEntity.getEpisodeId())
                .title(filmEntity.getTitle())
                .releaseDate(filmEntity.getReleaseDate())
                .build();
    }

    public static FilmEntity filmToEntity(Film film) {
        return FilmEntity.builder()
                .episodeId(film.getEpisodeId())
                .title(film.getTitle())
                .releaseDate(film.getReleaseDate())
                .build();
    }

    public static FilmEntity consumeResponseToEntity(FilmConsumerResponse filmConsumerResponse) {
        return FilmEntity.builder()
                .episodeId(filmConsumerResponse.getEpisodeId())
                .title(filmConsumerResponse.getTitle())
                .releaseDate(filmConsumerResponse.getReleaseDate())
                .build();
    }
}
