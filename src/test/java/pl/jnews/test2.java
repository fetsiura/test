//package pl.jnews;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import pl.jnews.core.weather.City;
//import pl.jnews.infrastructure.weatherdata.ResponseWeatherHandler;
//import pl.jnews.infrastructure.weatherdata.WeatherResponse;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//public class test2 {
//
//
//    public static void main(String[] args) {
//        String url_1 = "https://api.openweathermap.org/data/2.5/weather?lat=";
//        String url_2 = "&lon=";
//        String url_3 = "&APPID=";
//        RestTemplate restTemplate = new RestTemplate();
//        List<String> cities = new ArrayList<>();
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(" ","");
//        HttpEntity entity = new HttpEntity(httpHeaders);
//
//
//            String info ="Afghanistan;Kabul;34.516666666666666;69.183333";
//
//        String[] split = info.split(";");
//        String url = url_1+split[2]+url_2+split[3]+url_3+"d6db19042ae8152539fe0a5eb96edf51";
//        System.out.println(url);
//        ResponseEntity<ResponseWeatherHandler> exchange = restTemplate.exchange(url,
//                    HttpMethod.GET,
//                    entity,
//                    ResponseWeatherHandler.class);
//        ResponseWeatherHandler body = exchange.getBody();
//        System.out.println(body);
//
//        WeatherResponse weatherResponse = body.getWeather().get(0);
//        System.out.println("0000");
//        System.out.println(weatherResponse.getDescription());
//
//        City city = new City();
//
//        city.setName(split[1]);
//        city.setCountry(split[0]);
//        city.setLatitude(split[2]);
//        city.setLongitude(split[3]);
//        city.setClouds(body.getWeather().get(0).getDescription());
//        city.setHumidity(body.getMain().getHumidity());
//        city.setFeelsLike((short) (body.getMain().getFeels_like()-273.15));
//        city.setPressure(body.getMain().getPressure());
//        city.setTemperatura((short) (body.getMain().getTemp()-273.15));
//        city.setWindSpeed(body.getWind().getSpeed());
//
//
//        System.out.println(city);
//        }
//
//
//    public static List<String> readFromFile(String fileName){
//        List<String> list = new ArrayList<>();
//        Path path = Paths.get(fileName);
//        if(Files.exists(path)){
//            try {
//                list.addAll(Files.readAllLines(path));
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//        return list;
//    }
//}
