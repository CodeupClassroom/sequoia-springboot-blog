package com.codeup.sequoiaspringbootblog.services;

import com.codeup.sequoiaspringbootblog.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service  // Spring Bean
public class PostService {
    private List<Post> posts;

    public PostService() {
        this.posts = new ArrayList<>();
        createPosts();
    }

    private void createPosts() {
        save(new Post("Title A", "Body A"));
        save(new Post("Title B", "Body B"));
        save(new Post("Title C", "Body C"));
    }

    public void save(Post post) {
        post.setId(posts.size() + 1);
        this.posts.add(post);
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post findOne(long id) {
        return posts.get((int)id - 1);
    }
}
