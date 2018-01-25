package com.codeup.sequoiaspringbootblog.models;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    // We want the body to have an arbitrary length, we might have posts
    // that are longer than 255 characters, so we can define the column as type
    // text
    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    private User user;

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
