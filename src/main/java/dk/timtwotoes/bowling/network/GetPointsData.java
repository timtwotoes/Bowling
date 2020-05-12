package dk.timtwotoes.bowling.network;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GetPointsData {
    private Object points;
    private String token;

    public static GetPointsData fromJSON(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, GetPointsData.class);
    }

    public Object getPoints() {
        return points;
    }

    public String getToken() {
        return token;
    }
}
