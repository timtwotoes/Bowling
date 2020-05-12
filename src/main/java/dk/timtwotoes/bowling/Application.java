package dk.timtwotoes.bowling;

import dk.timtwotoes.bowling.logic.Frame;
import dk.timtwotoes.bowling.logic.FrameFactory;
import dk.timtwotoes.bowling.logic.Game;
import dk.timtwotoes.bowling.network.GetPointsData;
import dk.timtwotoes.bowling.network.PostPointsData;
import dk.timtwotoes.bowling.network.RESTClient;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static final URI POINTS_URI = URI.create("http://13.74.31.101/api/points");
    private RESTClient client = new RESTClient();
    private FrameFactory factory = new FrameFactory();

    private int[] computePoints(int[][] frames) {
        Game game = new Game(factory.generateFrames(FrameFactory.FrameKind.TEN_PIN, frames));
        return game.sumAllFramePoints();
    }

    public void run() throws Exception {
        HttpResponse<String> response = client.synchronousGetRequest(POINTS_URI);

        if (response.statusCode() == 200) {
            GetPointsData pointsData = GetPointsData.fromJSON(response.body());

            int[] computedPoints = computePoints(pointsData.getPoints());

            PostPointsData postData = new PostPointsData(pointsData.getToken(), computedPoints);
            HttpResponse<String> postResponse = client.synchronousPostRequest(POINTS_URI, postData.toJSON());

            if (postResponse.statusCode() == 200) {
                System.out.println("Computation was correct.");
            } else {
                System.out.println("Computation was incorrect.");
            }
        } else {
            System.out.println("Received status code " + response.statusCode());
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
