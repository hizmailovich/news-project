package by.epam.news.service.impl;

import by.epam.news.entity.Article;
import by.epam.news.repository.ArticleRepository;
import by.epam.news.service.ArticleService;
import by.epam.news.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getArticles(){
        List<Article> articles = (List<Article>) articleRepository.findAll();
        Collections.reverse(articles);
        return articles;
    }

    public ArrayList<Article> getArticleById(long id){
        Optional<Article> article = articleRepository.findById(id);
        ArrayList<Article> articles = new ArrayList<>();
        article.ifPresent(articles::add);
        return articles;
    }

    public void deleteArticle(long id){
        Article article = articleRepository.findById(id).orElseThrow();
        articleRepository.delete(article);
    }

    public void saveArticle(String title, String brief, String content){
        Article article = new Article(title, getDate(), brief, content);
        articleRepository.save(article);
    }

    public void editArticle(long id, String title, String brief, String content){
        Article article = articleRepository.findById(id).orElseThrow();
        article.setTitle(title);
        article.setBrief(brief);
        article.setDate(getDate());
        article.setContent(content);
        articleRepository.save(article);
    }

    private String getDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(Constants.DATE_PATTERN);
        return formatForDateNow.format(dateNow);
    }
}
