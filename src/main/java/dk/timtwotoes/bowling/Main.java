package dk.timtwotoes.bowling;

import dk.timtwotoes.bowling.network.RESTClient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        RESTClient client = new RESTClient();

        URI getPointsURI = URI.create("http://13.74.31.101/api/points");

        try {
            HttpResponse<String> response = client.synchronousGetRequest(getPointsURI);

            System.out.println(response.body());

        }
        catch (IOException e) { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }
}
