package com.mcsumdu.hritsay.specialty.models;

public class EducationService {
    private int serviceId;
    private String title;
    private String description;
    private Subject subject;
    private UrlAddress serviceUrl;
    private Group group;
    private Educator educator;

    public EducationService() {}

    public EducationService(int serviceId, String title, String description, Subject subject, UrlAddress serviceUrl, Group group, Educator educator) {
        this.serviceId = serviceId;
        this.title = title;
        this.description = description;
        this.subject = subject;
        this.serviceUrl = serviceUrl;
        this.group = group;
        this.educator = educator;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public UrlAddress getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(UrlAddress serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Educator getEducator() {
        return educator;
    }

    public void setEducator(Educator educator) {
        this.educator = educator;
    }

    @Override
    public String toString() {
        return "EducationService{" +
                "serviceId=" + serviceId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", subject=" + subject +
                ", serviceUrl=" + serviceUrl +
                ", group=" + group +
                ", educator=" + educator +
                '}';
    }
}


