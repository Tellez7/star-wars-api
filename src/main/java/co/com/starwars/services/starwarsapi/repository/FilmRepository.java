package co.com.starwars.services.starwarsapi.repository;

import co.com.starwars.services.starwarsapi.model.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
public interface FilmRepository extends JpaRepository<FilmEntity, Integer> {

}
