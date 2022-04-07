package pl.jnews.core.weather;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String country;
    private String clouds;
    private Short temperature;
    private Short feelsLike;
    private Short pressure;
    private Short humidity;
    private Double windSpeed;


    @Column(name = "updated")
    private LocalTime updated;


    @PreUpdate
    public void preUpdate() {
        updated = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
}
