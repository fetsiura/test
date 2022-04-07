package pl.jnews.core.weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.jnews.infrastructure.weatherdata.WeatherDataClient;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImplement implements CityService{


    private final CityRepository cityRepository;
    private final WeatherDataClient weatherDataClient;

    @Override
    public void addCityToDatabase() {
        log.info("start update weather database {}",LocalTime.now());
        cityRepository.saveAll(weatherDataClient.citiesWeatherHandler());
        log.info("Cities download to database {}", LocalTime.now());
    }

    public List<City> cityByTemperatureLowToHigh(){
        return cityRepository.findCitiesByTemperatureLowToHigh();
    }
    public List<City> cityByTemperatureHighToLow(){
        return cityRepository.findCitiesByTemperatureHighToLow();
    }
    public List<City> cityByNameASC(){
        return cityRepository.findCitiesByNameAtoZ();
    }
    public List<City> cityByNameDESC(){
        return cityRepository.findCitiesByNameZtoA();
    }
    public List<City> cityByWindSpeed(){
        return cityRepository.findCitiesByWindSpeed();
    }

    public Integer countAllCity(){
        return cityRepository.countAllCity();
    }

    public List<City> cityByNameStartWith(String name){
        return cityRepository.findByNameStartsWith(name.toUpperCase(Locale.ROOT));
    }

    public  void runUpdateCitiesTimer(){
        TimerTask cities = new TimerTask() {
            public void run() {
                addCityToDatabase();
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        /////////////////////sec    min    god
//        long period = 1000L * 60L * 60L * 24L;

        /////update pogody co 30 min
        long period = 1000L * 60L * 30L;
        timer.scheduleAtFixedRate(cities, delay, period);
    }
}
