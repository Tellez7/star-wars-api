package co.com.starwars.services.starwarsapi.consumer;

import co.com.starwars.services.starwarsapi.util.exception.RestTemplateResponseErrorHandler;
import co.com.starwars.services.starwarsapi.consumer.model.FilmConsumerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class FilmConsumer {

    private final RestTemplateBuilder restTemplateBuilder;

    private final String url;

    public FilmConsumer(RestTemplateBuilder restTemplateBuilder, @Value("${star-wars.url}") String url) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.url = url;
    }

    public FilmConsumerResponse consumer(Integer id) {
        return restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build()
                .getForEntity(url + "/" + id, FilmConsumerResponse.class)
                .getBody();
    }
}
