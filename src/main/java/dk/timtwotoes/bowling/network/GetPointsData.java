package dk.timtwotoes.bowling.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class GetPointsData {
    private int[][] points;
    private String token;

    private class JSONObject {
        private Object points;
        private String token;

        public Object getPoints() { return points; }
        public void setPoints(Object points) {this.points = points;}
        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }

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

        JSONObject object = mapper.readValue(jsonString, JSONObject.class);

        @SuppressWarnings("unchecked")
        List<List<Integer>> convertedPoints = (List<List<Integer>>)object.points;

        return new GetPointsData(convertedPoints, object.token);
    }

    public int[][] getPoints() {
        return points;
    }

    public String getToken() {
        return token;
    }
}
