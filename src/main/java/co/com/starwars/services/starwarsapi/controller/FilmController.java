package co.com.starwars.services.starwarsapi.controller;

import co.com.starwars.services.starwarsapi.model.dto.Film;
import co.com.starwars.services.starwarsapi.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/star-wars")
public class FilmController {

    private final FilmService filmService;

    @GetMapping("films/{id}")
    public ResponseEntity<Film> getByIdAndSave(@PathVariable String id) {
        return new ResponseEntity<>(filmService.findByIdFilms(id), HttpStatus.OK);
    }

    @PutMapping("films")
    public ResponseEntity<Film> update(@RequestBody Film film) {
        return new ResponseEntity<>(filmService.update(film), HttpStatus.OK);
    }

    @DeleteMapping("films/{id}")
    public ResponseEntity<Film> delete(@PathVariable String id) {
        filmService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
