package com.codeup.sequoiaspringbootblog.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ad {
    @Id
    private long id;
    private String title;
    private String description;

    public Ad(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Ad() {

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
