package pl.jnews.infrastructure.weatherdata;

import lombok.Data;

@Data
public class MainResponse {

    private Double temp;
    private Double feels_like;
    private Short pressure;
    private Short humidity;

}
