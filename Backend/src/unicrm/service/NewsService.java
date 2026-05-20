package unicrm.service;

import unicrm.domain.News;
import unicrm.repository.NewsRepository;

import java.util.List;

public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public News publishNews(String authorUsername, String title, String text) {
        News news = new News(title, text, authorUsername);
        newsRepository.save(news);
        return news;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
