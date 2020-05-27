package com.mcsumdu.hritsay.specialty.models;

public class Group {
    private int id;
    private String title;

    public Group(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Group() {}

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

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
