package com.codeup.sequoiaspringbootblog.models;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
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
