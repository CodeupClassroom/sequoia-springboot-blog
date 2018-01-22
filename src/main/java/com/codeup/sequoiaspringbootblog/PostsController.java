package com.codeup.sequoiaspringbootblog;

import com.codeup.sequoiaspringbootblog.models.Post;
import com.codeup.sequoiaspringbootblog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Page to search posts
// 1. Create a form in the navigation bar
//    - Find the fragment and add a new one for the search form
// 2. Create a controller action to perform the search
//    - Create a SearchController
//    - Method to search by a term (GetMapping /search)
// 3. Write code to query the DB using the term sent by the user
///   - SearchService, will have a method search(term) -> select from posts where title like %term%
//    - probably start with an empty list, then query the DB
// 4 Create/reuse a template to loop over the result of the search
//    public String search(@PathVariable String term) {
//     return 'post/index';  // instead of 'posts/search-result'
//    }

// Dependency injection
// 1. Constructor injection (preferred) -> required dependencies
// 2. Setter injection -> optional dependencies

@Controller
public class PostsController {
    // 1. Create an instance variable with your dependency
    private final PostService postService;

    // 2. Inject the dependency through the constructor and assign it to your instance variable
    public PostsController(PostService postService) {
        this.postService = postService; // This the first time we assign something to postService
    }

    @RequestMapping("/posts")
    public String index(Model viewAndModel) {
        /*List<Post> posts = Arrays.asList(
            new Post("Post A", "Body A"),
            new Post("Post B", "Body B"),
            new Post("Post C", "Body C")
        );*/
        Iterable<Post> posts = postService.findAll();

        viewAndModel.addAttribute("posts", posts);

        return "posts/index";
    }

    @RequestMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewAndModel) {
        //Post post = new Post("Test post", "Test body");
        Post post = postService.findOne(id);

        viewAndModel.addAttribute("post", post);

        return "posts/show";
    }

    @RequestMapping("/posts/create")
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model viewAndModel) {
        Post post = postService.findOne(id);
        viewAndModel.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/edit")
    public String updatePost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts";
    }
}
