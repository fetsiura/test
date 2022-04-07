package pl.jnews.infrastructure.weatherdata;


import lombok.Data;

import java.util.List;

@Data
public class ResponseWeatherHandler {

    private List<WeatherResponse> weather;
    private MainResponse main;
    private WindResponse wind;

}
