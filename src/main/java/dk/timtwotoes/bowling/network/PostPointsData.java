package dk.timtwotoes.bowling.network;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostPointsData {
    private String token;
    private List<Integer> points;

    public PostPointsData(String token, int[] points) {
        this.token = token;

        this.points = new ArrayList<>(points.length);
        for (int value: points) {
            this.points.add(value);
        }
    }

    public String getToken() {
        return token;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writer().writeValueAsString(this);
    }
}
