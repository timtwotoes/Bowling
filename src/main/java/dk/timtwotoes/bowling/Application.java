package dk.timtwotoes.bowling;

import dk.timtwotoes.bowling.logic.Game;
import dk.timtwotoes.bowling.network.GetPointsData;
import dk.timtwotoes.bowling.network.PostPointsData;
import dk.timtwotoes.bowling.network.RESTClient;
import dk.timtwotoes.bowling.network.SuccessData;

import java.net.URI;
import java.net.http.HttpResponse;

public class Application {
    private static final URI POINTS_URI = URI.create("http://13.74.31.101/api/points");
    private RESTClient client = new RESTClient();

    private int[] computePoints(int[][] frames) {
        Game game = new Game.Builder(Game.Builder.Option.TEN_PIN, frames).build();
        return game.sumAllFramePoints();
    }

    public void run() throws Exception {
        System.out.println("Requesting Token");
        HttpResponse<String> response = client.synchronousGetRequest(POINTS_URI);

        if (response.statusCode() == 200) {
            GetPointsData pointsData = GetPointsData.fromJSON(response.body());

            System.out.println("Received " + response.body());

            int[] computedPoints = computePoints(pointsData.getPoints());

            PostPointsData postData = new PostPointsData(pointsData.getToken(), computedPoints);
            String jsonString = postData.toJSON();
            System.out.println("Sending: " + jsonString );
            HttpResponse<String> postResponse = client.synchronousPostRequest(POINTS_URI, jsonString);

            if (postResponse.statusCode() == 200) {
                SuccessData successData = SuccessData.fromJSON(postResponse.body());
                System.out.println("Received Success = " + successData.getSuccess().toString());
            } else {
                System.out.println("[" + postResponse.statusCode() +"] " + postResponse.body());
            }
        } else {
            System.out.println("[" + response.statusCode() + "] " + response.body());
        }
    }

    public static void main(String[] args) {
        Application application = new Application();

        try {
            application.run();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}
