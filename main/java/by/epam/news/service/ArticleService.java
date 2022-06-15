package by.epam.news.service;

import by.epam.news.entity.Article;

import java.util.*;

public interface ArticleService {

    /**
     * Method for getting a list of articles
     *
     * @return list of articles
     */
    List<Article> getArticles();

    /**
     * Method for getting a list of articles by id
     *
     * @param id article's id
     * @return list of articles
     */
    ArrayList<Article> getArticleById(long id);

    /**
     * Method for deleting article by id
     *
     * @param id article's id
     */
    void deleteArticle(long id);

    /**
     * Method for saving article by id
     *
     * @param title article's title
     * @param brief article's brief
     * @param content article's content
     */
    void saveArticle(String title, String brief, String content);

    /**
     * Method for editing article by id
     *
     * @param id article's id
     * @param title article's title
     * @param brief article's brief
     * @param content article's content
     */
    void editArticle(long id, String title, String brief, String content);
}
