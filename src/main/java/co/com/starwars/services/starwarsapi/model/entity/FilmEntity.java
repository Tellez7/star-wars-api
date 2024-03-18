package co.com.starwars.services.starwarsapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "films")
public class FilmEntity implements Serializable {

    @Id
    @Column(name = "episode_id")
    private Integer episodeId;

    @Column(name = "title")
    private String title;

    @Column(name = "release_date")
    private Date releaseDate;

}
