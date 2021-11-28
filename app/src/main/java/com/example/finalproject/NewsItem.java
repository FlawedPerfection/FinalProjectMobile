package com.example.finalproject;

import java.util.UUID;

public class NewsItem {
    String title;
    String description;
    String link;
    String pubDate;


    String guid = UUID.randomUUID().toString();

    public NewsItem() {

    }


    public NewsItem(String title, String pubDate) {
        this.title = title;
        this.pubDate = pubDate;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
