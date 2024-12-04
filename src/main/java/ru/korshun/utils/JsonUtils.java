package ru.korshun.utils;

import org.json.JSONObject;
import ru.korshun.BrawlStarsAPI;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonUtils {
    public static String API_TOKEN = BrawlStarsAPI.getApiKey();
    public static JSONObject readJsonFromUrl(String url) {
        if(url.contains("#")) {
           url = url.replace("#", "%23");
        }
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + API_TOKEN)
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}