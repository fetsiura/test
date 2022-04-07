package pl.jnews.core.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
interface CityRepository extends JpaRepository<City,Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM cities ORDER BY temperature ASC")
    List<City> findCitiesByTemperatureLowToHigh();


    @Query(nativeQuery = true,
            value = "SELECT * FROM cities ORDER BY temperature DESC")
    List<City> findCitiesByTemperatureHighToLow();

    @Query(nativeQuery = true,
            value = "SELECT * FROM cities ORDER BY name ASC")
    List<City> findCitiesByNameAtoZ();

    @Query(nativeQuery = true,
            value = "SELECT * FROM cities ORDER BY name DESC")
    List<City> findCitiesByNameZtoA();

    @Query(nativeQuery = true,
            value = "SELECT * FROM cities ORDER BY wind_Speed DESC")
    List<City> findCitiesByWindSpeed();

    @Query(nativeQuery = true,
    value = "SELECT COUNT(*) FROM CITIES")
    Integer countAllCity();

    List<City> findByNameStartsWith(String name);
}
