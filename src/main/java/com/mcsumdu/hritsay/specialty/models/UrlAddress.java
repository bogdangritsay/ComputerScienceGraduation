package com.mcsumdu.hritsay.specialty.models;

public class UrlAddress {
    private int urlId;
    private String url;
    private String urlType;

    public UrlAddress() {}

    public UrlAddress(String url, String urlType) {
        this.url = url;
        this.urlType = urlType;
    }

    public UrlAddress(int urlId, String url, String urlType) {
        this.urlId = urlId;
        this.url = url;
        this.urlType = urlType;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    @Override
    public String toString() {
        return "UrlAddress{" +
                "urlId=" + urlId +
                ", url='" + url + '\'' +
                ", urlType='" + urlType + '\'' +
                '}';
    }
}
