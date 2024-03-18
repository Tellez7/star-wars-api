package co.com.starwars.services.starwarsapi.consumer;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import co.com.starwars.services.starwarsapi.consumer.model.FilmConsumerResponse;
import co.com.starwars.services.starwarsapi.util.exception.RestTemplateResponseErrorHandler;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class FilmConsumerTest {
    private FilmConsumer filmConsumer;
    private FilmConsumerResponse filmConsumerResponse;
    @Autowired
    private RestTemplateBuilder restTemplateBuilderResponse;
    private Integer id;
    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${star-wars.url}")
    private String url;

    @BeforeEach
    void setUp() {
        restTemplateBuilderResponse = restTemplateBuilderResponse
                .errorHandler(new RestTemplateResponseErrorHandler());
        id = 1;
        filmConsumerResponse = FilmConsumerResponse.builder()
                .episodeId(4)
                .title("A New Hope")
                .releaseDate(new Date())
                .build();
        filmConsumer = new FilmConsumer(restTemplateBuilder, url);
    }

    @Test
    void consumer() {
        when(restTemplateBuilder.errorHandler(any())).thenReturn(restTemplateBuilderResponse);

        when(restTemplateBuilder.build(any())).thenReturn(new RestTemplate());

        FilmConsumerResponse filmResponse = filmConsumer.consumer(id);

        assertNotNull(filmResponse);
        assertEquals(filmResponse.getEpisodeId(), filmConsumerResponse.getEpisodeId());
    }
}