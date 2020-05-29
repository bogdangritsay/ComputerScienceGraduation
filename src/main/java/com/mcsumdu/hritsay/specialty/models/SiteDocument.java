package com.mcsumdu.hritsay.specialty.models;

public class SiteDocument {

    private int docId;
    private String title;
    private String description;
    private UrlAddress urlDoc;

    public SiteDocument() {}

    public SiteDocument(int docId, String title, String description, UrlAddress urlDoc) {
        this.docId = docId;
        this.title = title;
        this.description = description;
        this.urlDoc = urlDoc;
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

    public UrlAddress getUrlDoc() {
        return urlDoc;
    }

    public void setUrlDoc(UrlAddress urlDoc) {
        this.urlDoc = urlDoc;
    }

    @Override
    public String toString() {
        return "SiteDocument{" +
                "docId=" + docId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", urlDocId=" + urlDoc +
                '}';
    }
}
