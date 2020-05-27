package com.mcsumdu.hritsay.specialty.controllers;

import com.mcsumdu.hritsay.specialty.dao.NewsPostgresDAO;
import com.mcsumdu.hritsay.specialty.dao.UrlsPostgresDAO;
import com.mcsumdu.hritsay.specialty.models.News;
import com.mcsumdu.hritsay.specialty.models.UrlAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class NewsController {
    @Autowired
    private NewsPostgresDAO newsConnection;


    @GetMapping("/news")
    public String newsMain(Model model) {
        Iterable<News> news = newsConnection.getAllNews();
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/news/{id}")
    public String newsDetails(@PathVariable(value = "id") int id, Model model) {
        ArrayList<News> newsList = new ArrayList<>();
        News news = newsConnection.getNewsById(id);

        if(news != null)  newsList.add(news);
        model.addAttribute("news", newsList);
        return "news-details";
    }



}
