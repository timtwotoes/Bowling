package dk.timtwotoes.bowling.network;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GetPointsAPI {
    private Object points;
    private String token;

    public static GetPointsAPI fromJSON(String jsonString) throws IOException, JsonParseException, JsonMappingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, GetPointsAPI.class);
    }

    public Object getPoints() {
        return points;
    }

    public String getToken() {
        return token;
    }
}
