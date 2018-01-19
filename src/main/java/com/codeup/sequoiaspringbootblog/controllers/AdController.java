package com.codeup.sequoiaspringbootblog.controllers;

import com.codeup.sequoiaspringbootblog.models.Ad;
import com.codeup.sequoiaspringbootblog.services.AdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {
    // Auto-wiring
    private AdService service;

    public AdController(AdService service) {
        this.service = service;
    }

    @GetMapping("/ads")
    public String showAllAds(Model viewAndModel) {
        List<Ad> ads = service.findAll();

        viewAndModel.addAttribute("ads", ads);

        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showIndividualAd(@PathVariable long id, Model viewAndModel) {
        Ad ad = service.findOne(id);

        viewAndModel.addAttribute("ad", ad);

        return "ads/show";
    }

    @GetMapping("/ads/new")
    public String showCreateAdForm() {
        return "ads/new";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String saveAd(@RequestParam("title") String title, @RequestParam("description") String description) {
        Ad ad = new Ad(title, description);
        return ad.getTitle() + " " + ad.getDescription()    ;
    }
}
