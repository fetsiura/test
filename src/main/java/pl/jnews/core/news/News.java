package pl.jnews.core.news;

import lombok.Data;
import pl.jnews.infrastructure.newsdata.NewsResponse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String url;
    private String urlToImage;
    private String description;
    private String source;
}
