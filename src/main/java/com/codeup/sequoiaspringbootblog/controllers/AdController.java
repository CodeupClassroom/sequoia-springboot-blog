package com.codeup.sequoiaspringbootblog.controllers;

import com.codeup.sequoiaspringbootblog.models.Ad;
import com.codeup.sequoiaspringbootblog.services.AdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String showIndividualAd(@PathVariable long id) {
        return "";
    }
}
