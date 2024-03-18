package co.com.starwars.services.starwarsapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
