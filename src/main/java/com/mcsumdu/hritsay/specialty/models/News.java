package com.mcsumdu.hritsay.specialty.models;



import java.sql.Date;

public class News {
    private int id;
    private String title;
    private String description;
    private String text;
    private Date date;
    private  String imgUrl;

    public News(int id, String title, String description, String text, Date date, String imgUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.text = text;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    public News(String title, String description, String text, Date date, String imgUrl) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.date = date;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public News() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
