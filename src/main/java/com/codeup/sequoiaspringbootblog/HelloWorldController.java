package com.codeup.sequoiaspringbootblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("/say/{greeting}/to/{name}")
    @ResponseBody
    public String greet(@PathVariable String greeting, @PathVariable String name) {
        return greeting + " " + name + "!";
    }

    // http://foxsanantonio.com/news/local/icy-conditions-in-san-antonio-hill-country
    @GetMapping("/news/local/{title}")
    public String showArticle(@PathVariable String title) {
       // query the database for article where title like ?
       // return a html response with the article contents
        return "";
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloTo(@PathVariable String name, Model viewModel) {
        viewModel.addAttribute("name", name);
        return "hello";
    }

}
