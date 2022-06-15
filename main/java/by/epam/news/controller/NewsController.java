package by.epam.news.controller;

import by.epam.news.entity.Article;
import by.epam.news.service.impl.ArticleServiceImpl;
import by.epam.news.utils.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class NewsController {
    private static final Logger logger = LogManager.getLogger(NewsController.class);

    @Autowired
    ArticleServiceImpl articleServiceImpl;

    @GetMapping("/news/home")
    public String transmitToHomePage(Model model) {
        List<Article> articles = articleServiceImpl.getArticles();
        model.addAttribute(Constants.NEWS_NAME, articles);
        return Constants.HOME_PAGE;
    }

    @PostMapping("/news/home")
    public String deleteNews(@RequestParam(defaultValue = Constants.EMPTY_NAME) String[] checkBox,
                                             @RequestParam(defaultValue = Constants.EMPTY_NAME) String deleteId,
                                             Model model) {
        if (!deleteId.equals(Constants.EMPTY_NAME)) {
            articleServiceImpl.deleteArticle(Long.parseLong(deleteId));
        } else if (checkBox[0].equals(Constants.EMPTY_NAME)) {
            logger.info("Nothing to delete!");
        } else {
            for (String id : checkBox) {
                articleServiceImpl.deleteArticle(Long.parseLong(id));
            }
            logger.info("Successfully deleted!");
        }
        return Constants.REDIRECT_HOME_PAGE;
    }

    @GetMapping("/news/add")
    public String transmitToAddPage(Model model) {
        List<Article> articles = articleServiceImpl.getArticles();
        model.addAttribute(Constants.NEWS_NAME, articles);
        return Constants.ADD_PAGE;
    }

    @PostMapping("/news/add")
    public String addNews(@RequestParam String title,
                                          @RequestParam String brief,
                                          @RequestParam String content,
                                          Model model) {
        articleServiceImpl.saveArticle(title, brief, content);
        logger.info("Successfully added!");
        return Constants.REDIRECT_HOME_PAGE;
    }

    @GetMapping("/news/{id}/edit")
    public String transmitToEditPage(@PathVariable(value = Constants.ID_NAME) long id,
                                     Model model) {
        ArrayList<Article> articles = articleServiceImpl.getArticleById(id);
        model.addAttribute(Constants.NEWS_NAME, articles);
        return Constants.EDIT_PAGE;
    }

    @PostMapping("/news/{id}/edit")
    public String editNews(@PathVariable(value = Constants.ID_NAME) long id,
                                           @RequestParam String title,
                                           @RequestParam String brief,
                                           @RequestParam String content,
                                           Model model) {
        articleServiceImpl.editArticle(id, title, brief, content);
        logger.info("Successfully edited!");
        return Constants.REDIRECT_HOME_PAGE;
    }

    @GetMapping("/news/{id}/view")
    public String viewNews(@PathVariable(value = Constants.ID_NAME) long id,
                                     Model model) {
        ArrayList<Article> articles = articleServiceImpl.getArticleById(id);
        model.addAttribute(Constants.NEWS_NAME, articles);
        return Constants.VIEW_PAGE;
    }
}
