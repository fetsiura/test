package pl.jnews.core.news;

import java.util.List;

interface NewsService {

    List<News> getNewsWhenInputEmpty();
    List<News> getNewsWithCategory(String category);

}
