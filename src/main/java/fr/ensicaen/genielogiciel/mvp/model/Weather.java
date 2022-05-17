package fr.ensicaen.genielogiciel.mvp.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    private String _windDirectory;
    private String _windSpeed;
    private String _url;
    public  Weather(String url) {
        _url=url;
        displayWeather(_url);
    }
    public void displayWeather(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection;
            connection = (HttpURLConnection) url.openConnection();
            StringBuilder inputLine= new StringBuilder();
            connection.connect();
            int response = connection.getResponseCode();
            Scanner in = new Scanner(url.openStream());
            while (in.hasNext()) {
                inputLine.append(in.nextLine());
            }
            JsonObject jsonObject = new JsonParser().parse(inputLine.toString()).getAsJsonObject();
            JsonElement currentCondition = jsonObject.get("current_condition");
            _windDirectory = currentCondition.getAsJsonObject().get("wnd_dir").getAsString();
            _windSpeed = currentCondition.getAsJsonObject().get("wnd_spd").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String get_windDirectory() {
        return _windDirectory;
    }

    public String get_windSpeed() {
        return _windSpeed;
    }
}
