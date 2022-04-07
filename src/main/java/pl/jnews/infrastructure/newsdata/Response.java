package pl.jnews.infrastructure.newsdata;

import lombok.Data;

import java.util.List;

@Data
class Response {

    private Integer totalResults;
    private List<NewsResponse> articles;
}
