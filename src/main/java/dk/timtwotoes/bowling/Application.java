package dk.timtwotoes.bowling;

import dk.timtwotoes.bowling.network.GetPointsData;
import dk.timtwotoes.bowling.network.RESTClient;
import java.net.URI;
import java.net.http.HttpResponse;

public class Application {
    private static final URI POINTS_URI = URI.create("http://13.74.31.101/api/points");
    private RESTClient client = new RESTClient();

    public void run() throws Exception {
        HttpResponse<String> response = client.synchronousGetRequest(POINTS_URI);

        if (response.statusCode() == 200) {
            GetPointsData pointsData = GetPointsData.fromJSON(response.body());

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
