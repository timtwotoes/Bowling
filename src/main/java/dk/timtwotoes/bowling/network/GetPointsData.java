package dk.timtwotoes.bowling.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class GetPointsData {
    private int[][] points;
    private String token;

    public GetPointsData(List<List<Integer>> points, String token) {
        int[][] newPoints = new int[points.size()][];

        for (int index = 0; index < newPoints.length; index++) {
            newPoints[index] = points.get(index).stream().mapToInt(i -> i).toArray();
        }
        this.points = newPoints;
        this.token = token;
    }

    public static GetPointsData fromJSON(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        JSONPointsData object = mapper.readValue(jsonString, JSONPointsData.class);

        @SuppressWarnings("unchecked")
        List<List<Integer>> convertedPoints = (List<List<Integer>>)object.getPoints();

        return new GetPointsData(convertedPoints, object.getToken());
    }

    public int[][] getPoints() {
        return points;
    }

    public String getToken() {
        return token;
    }
}

class JSONPointsData {
    private Object points;
    private String token;

    public Object getPoints() { return points; }
    public String getToken() { return token; }
}