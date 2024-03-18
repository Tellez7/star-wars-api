package co.com.starwars.services.starwarsapi.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
public class Film implements Serializable {

    private Integer episodeId;

    private String title;

    private Date releaseDate;
}
