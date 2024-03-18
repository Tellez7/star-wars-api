package co.com.starwars.services.starwarsapi.model.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
public class MessageResponse implements Serializable {

    private String message;

}
