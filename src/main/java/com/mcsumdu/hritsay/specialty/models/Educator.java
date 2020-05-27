package com.mcsumdu.hritsay.specialty.models;

public class Educator {
    private int educatorId;
    private String name;
    private String surname;
    private String patronymic;
    private String description;
    private UrlAddress urlToImage;
    private Educator manager;
    private Role role;

    public Educator() {}

    public Educator(int educatorId, String name, String surname, String patronymic, String description, UrlAddress urlToImage, Educator manager, Role role) {
        this.educatorId = educatorId;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.description = description;
        this.urlToImage = urlToImage;
        this.manager = manager;
        this.role = role;
    }

    public Educator(String name, String surname, String patronymic, String description, UrlAddress urlToImage, Educator manager, Role role) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.description = description;
        this.urlToImage = urlToImage;
        this.manager = manager;
        this.role = role;
    }

    public int getEducatorId() {
        return educatorId;
    }

    public void setEducatorId(int educatorId) {
        this.educatorId = educatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UrlAddress getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(UrlAddress urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Educator getManager() {
        return manager;
    }

    public void setManager(Educator manager) {
        this.manager = manager;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Educator{" +
                "educatorId=" + educatorId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", description='" + description + '\'' +
                ", urlToImage=" + urlToImage +
                ", manager=" + manager +
                ", role=" + role;
    }
}
