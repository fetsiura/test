package pl.jnews.infrastructure.newsdata;

import lombok.Data;

@Data
public class NewsResponse {

    private String title;
    private String url;
    private String urlToImage;
    private String description;
    private Source source;



}
