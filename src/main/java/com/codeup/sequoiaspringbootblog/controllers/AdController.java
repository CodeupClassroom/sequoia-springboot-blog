package com.codeup.sequoiaspringbootblog.controllers;

import com.codeup.sequoiaspringbootblog.models.Ad;
import com.codeup.sequoiaspringbootblog.models.User;
import com.codeup.sequoiaspringbootblog.services.AdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {
    // Auto-wiring
    private AdService adsService;

    public AdController(AdService service) {
        this.adsService = service;
    }

    @GetMapping("/ads")
    public String showAllAds(Model viewAndModel) {
        Iterable<Ad> ads = adsService.findAll();

        viewAndModel.addAttribute("ads", ads);

        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String showIndividualAd(@PathVariable long id, Model viewAndModel) {
        Ad ad = adsService.findOne(id);

        viewAndModel.addAttribute("ad", ad);

        return "ads/show";
    }

    @GetMapping("/ads/new")
    public String showCreateAdForm(Model vModel) {
        Ad ad = new Ad();
        vModel.addAttribute("ad", ad);
        return "ads/new";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String saveAd(@ModelAttribute Ad ad) {
        adsService.save(ad);
        return ad.getTitle() + " " + ad.getDescription();
    }

    @ResponseBody
    @GetMapping("/tests")
    public String test() {
        User user = new User("zach", "codeup");
        // Setter injection
        //Ad ad = new Ad("Ad title", "Ad description");
        // Somebody might forget to call the setter, this is why it's called optional dependency
        //ad.setOwner(user);
        // Constructor injection is required, because there is no way to create a Post without a User
        Ad ad = new Ad("Ad title", "Ad description", user);
        adsService.save(ad);
        return "Saved!";
    }

}
