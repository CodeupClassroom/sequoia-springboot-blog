package com.codeup.sequoiaspringbootblog.models;

import javax.persistence.*;

@Entity
@Table(name="ad_images")
public class AdImage {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String fileName;

    @ManyToOne
    private Ad ad;
}