package com.mcsumdu.hritsay.specialty.dao;

import com.mcsumdu.hritsay.specialty.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsPostgresDAO extends PostgresDAOConnection {
    @Autowired
    private UrlsPostgresDAO urlsPostgresDAO;

    public List<News> getAllNews() {
        connect();
        List<News> news = new ArrayList<>();
        try {
            statement = connection.prepareStatement("select n.news_id, n.title, n.short_desc, n.news_text, n.news_date, u.url from site_news n, urls u where u.url_id = n.url_img_id");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                News tmpNews = parseNews(resultSet);

                news.add(tmpNews);
            }
        } catch (SQLException e) {
            /*
            * LOGS
             */

        } finally {
            disconnect();
        }
        return news;
    }

    public List<News> getThreeLastNews() {
        connect();
        List<News> news = new ArrayList<>();
        try {
            statement = connection.prepareStatement(
                    "select n.news_id, n.title, n.short_desc, n.news_text, n.news_date, u.url " +
                        "from site_news n, urls u " +
                        "where u.url_id = n.url_img_id " +
                        "ORDER by n.news_id DESC " +
                        "LIMIT 3"
            );
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                News tmpNews = parseNews(resultSet);

                news.add(tmpNews);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */

        } finally {
            disconnect();
        }
        return news;
    }

    public News getNewsById(int id) {
        connect();
        News news = null;
        try {
            statement = connection.prepareStatement("select n.news_id, n.title, n.short_desc, n.news_text, n.news_date, u.url from site_news n, urls u where u.url_id = n.url_img_id  and NEWS_ID = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                news= parseNews(resultSet);
            }
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }

        return news;
    }

    public void addNews(News news) {

        if(!urlsPostgresDAO.existsUrlByString(news.getImgUrl())) {
            urlsPostgresDAO.addNewUrl(news.getImgUrl(), "img");
        }

        int imgUrlId = urlsPostgresDAO.getUrlIdByString(news.getImgUrl());


        connect();
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO SITE_NEWS(TITLE, SHORT_DESC, NEWS_TEXT, NEWS_DATE, URL_IMG_ID)" +
                        "VALUES (?, ?, ?, ?, ?)"
            );

            statement.setString(1, news.getTitle());
            statement.setString(2, news.getDescription());
            statement.setString(3, news.getText());
            statement.setDate(4, news.getDate());
            statement.setInt(5, imgUrlId);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    public void updateNews(int id, News news) {

        try {

            if(!urlsPostgresDAO.existsUrlByString(news.getImgUrl())) {
                urlsPostgresDAO.addNewUrl(news.getImgUrl(), "img");
            }

            int imgUrlId = urlsPostgresDAO.getUrlIdByString(news.getImgUrl());



            connect();
            statement = connection.prepareStatement(
                    "UPDATE SITE_NEWS" +
                    " SET title = ?," +
                    "short_desc = ?," +
                    "news_text = ?," +
                    "news_date = ?," +
                    "url_img_id = ? " +
                    "WHERE NEWS_ID = ?"
            );
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getDescription());
            statement.setString(3, news.getText());
            statement.setDate(4, news.getDate());
            statement.setInt(5, imgUrlId);
            statement.setInt(6, id);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
    }

    public void deleteNews(int id) {
        connect();
        try {
            statement = connection.prepareStatement("DELETE FROM SITE_NEWS WHERE NEWS_ID = ?");
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            /*
             * LOGS
             */
        } finally {
            disconnect();
        }
    }

    private News parseNews(ResultSet resultSet) {
        News news = null;
        try {
            UrlsPostgresDAO urlImg = new UrlsPostgresDAO();
            int id = resultSet.getInt("NEWS_ID");
            String title = resultSet.getString("TITLE");
            String shortDesc = resultSet.getString("SHORT_DESC");
            String newsText = resultSet.getString("NEWS_TEXT");
            Date date = resultSet.getDate("NEWS_DATE");
            String imgUrl = resultSet.getString("URL");
            news = new News(id, title, shortDesc, newsText, date, imgUrl);
        } catch (SQLException e) {
            /*
            LOGS
             */
        }
        return news;
    }


}
