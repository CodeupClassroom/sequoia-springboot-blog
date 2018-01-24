package com.codeup.sequoiaspringbootblog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class AdCategory {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Ad> ads;
}
