package com.mcsumdu.hritsay.specialty.controllers.admin;

import com.mcsumdu.hritsay.specialty.dao.NewsPostgresDAO;
import com.mcsumdu.hritsay.specialty.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;

@Controller
public class NewsAdminController {
    @Autowired
    private NewsPostgresDAO newsConnection;


    @GetMapping("/news-admin")
    public String newsMain(Model model) {
        Iterable<News> news = newsConnection.getAllNews();
        model.addAttribute("news", news);
        return "admin_pages/news-admin";
    }

    @GetMapping("/news-admin/{id}")
    public String newsDetails(@PathVariable(value = "id") int id, Model model) {
        ArrayList<News> newsList = new ArrayList<>();
        News news = newsConnection.getNewsById(id);

        if (news != null) newsList.add(news);
        model.addAttribute("news", newsList);
        return "admin_pages/news-details-admin";
    }


    @GetMapping("/news-admin/{id}/edit")
    public String newsEdit(@PathVariable(value = "id") int id, Model model) {
        ArrayList<News> newsList = new ArrayList<>();
        News news = newsConnection.getNewsById(id);
        if (news != null) newsList.add(news);
        model.addAttribute("news", newsList);
        return "admin_pages/news-edit-admin";
    }


    @GetMapping("/news-admin/add")
    public String newsAdd( Model model) {
        return "admin_pages/news-add-admin";
    }

    @PostMapping("/news-admin/add")
    public String newsAddPost( @RequestParam String title,
                               @RequestParam String description,
                               @RequestParam Date date,
                               @RequestParam String imgUrl,
                               @RequestParam String text,
                               Model model) {
        News news = new News(title, description, text, date, imgUrl);
        System.out.println(news);
        newsConnection.addNews(news);

        return "redirect:/news-admin";
    }



    @PostMapping("/news-admin/{id}/edit")
    public String newsUpdate(@PathVariable(value = "id") int id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Date date,
                             @RequestParam String text,
                             @RequestParam String imgUrl,
                             Model model) {
        News news = newsConnection.getNewsById(id);
        news.setTitle(title);
        news.setDescription(description);
        news.setDate(date);
        news.setText(text);
        news.setImgUrl(imgUrl);
        newsConnection.updateNews(id, news);
        return "redirect:/news-admin";
    }

    @PostMapping("/news-admin/{id}/remove")
    public String newsDelete(@PathVariable(value = "id") int id, Model model) {
        newsConnection.deleteNews(id);
        return "redirect:/news-admin";
    }
}
