package pl.jnews.infrastructure.newsdata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.jnews.core.news.News;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewsDataClient {

    private final String TOKEN_1 = "tokend68ab16501944c5682a2f2c79945e4a6token";
    private final String TOKEN_2 = "token27caad093f934fbfb3793aa077315440token";
    private final String TOKEN_3 = "token02c5564a2cfd4a3c9cf537e2ea0e600etoken";
    private final String TOKEN_4 = "token1b1ecb3c525944aea82e789e45768979token";
    private int APICounter = 1;

    private final RestTemplate restTemplate;

    public List<News> newsHandler (String url){
        String TOKENIZER = TOKEN_1;
        if(APICounter ==90){
            TOKENIZER =TOKEN_2;
        } else if(APICounter ==180){
            TOKENIZER =TOKEN_3;
        } else if(APICounter ==270){
            TOKENIZER =TOKEN_4;
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity entity =new HttpEntity(httpHeaders);
        httpHeaders.add("X-Api-Key",TOKENIZER.substring(5,TOKENIZER.length()-5));
        ResponseEntity<Response> response = restTemplate.exchange(url,
                HttpMethod.GET,
                entity,
                Response.class);

        Response body = response.getBody();

        List<NewsResponse> articles = body.getArticles();

        List<News> convertedNews = new ArrayList<>();

        for(NewsResponse newsResponse : articles){
            News converter = new News();
            converter.setTitle(newsResponse.getTitle());
            converter.setUrl(newsResponse.getUrl());
            converter.setUrlToImage(newsResponse.getUrlToImage());
            converter.setDescription(wordLengthLimit(newsResponse.getDescription()));
            converter.setSource(newsResponse.getSource().getName());
            convertedNews.add(converter);
        }
        log.info("APINewsCounter - {}", APICounter++);
        return convertedNews;

    }

    public String wordLengthLimit(String word){
        if(word ==null){
            word="";
        }
        if(word.length()>176){
            word=word.substring(0,176).concat("...");
        }
        return word;
    }
}
