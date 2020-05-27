package com.mcsumdu.hritsay.specialty.models;

public class SiteDocument {

    private int docId;
    private String title;
    private String description;
    private int urlDocId;

    public SiteDocument() {}

    public SiteDocument(int docId, String title, String description, int urlDocId) {
        this.docId = docId;
        this.title = title;
        this.description = description;
        this.urlDocId = urlDocId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
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

    public int getUrlDocId() {
        return urlDocId;
    }

    public void setUrlDocId(int urlDocId) {
        this.urlDocId = urlDocId;
    }

    @Override
    public String toString() {
        return "SiteDocument{" +
                "docId=" + docId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", urlDocId=" + urlDocId +
                '}';
    }
}
