package dk.timtwotoes.bowling.network;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class PostPointsAPI {
    private String token;
    private List<Integer> points;

    public PostPointsAPI(String token, List<Integer> points) {
        this.token = token;
        this.points = points;
    }

    public String getToken() {
        return token;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public String toJSON() throws IOException, JsonMappingException, JsonParseException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }
}
