package co.com.starwars.services.starwarsapi.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Builder
public class Film implements Serializable {

    private Integer episodeId;

    private String title;

    private Date releaseDate;
}
