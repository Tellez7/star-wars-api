package co.com.starwars.services.starwarsapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.com.starwars.services.starwarsapi.model.dto.Film;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FilmControllerTest {

    @Autowired
    private MockMvc mvc;
    private String basePath;
    private String id;
    private Film film;

    @BeforeEach
    void setUp() {
        id = "4";
        basePath = "/star-wars/films";
        film = Film.builder()
                .episodeId(1)
                .title("Revenge")
                .releaseDate(new Date())
                .build();
    }

    @Test
    @Order(1)
    void getByIdAndSave() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get(basePath + "/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate").exists());
    }

    @Test
    @Order(2)
    public void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put(basePath)
                        .content(asJsonString(film))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.episodeId").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseDate").exists());
    }

    @Test
    @Order(3)
    void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete(basePath + "/" + film.getEpisodeId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(Film film) {
        try {
            return new ObjectMapper().writeValueAsString(film);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}