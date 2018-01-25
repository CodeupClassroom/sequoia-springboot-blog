package com.codeup.sequoiaspringbootblog.controllers;

import com.codeup.sequoiaspringbootblog.daos.UsersRepository;
import com.codeup.sequoiaspringbootblog.models.Ad;
import com.codeup.sequoiaspringbootblog.models.User;
import com.codeup.sequoiaspringbootblog.services.AdService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdController {
    // Auto-wiring
    private AdService adsService;
    private UsersRepository usersRepository;

    public AdController(AdService service, UsersRepository usersRepository) {
        this.adsService = service;
        this.usersRepository = usersRepository;
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
    public String saveAd(@ModelAttribute Ad ad) {

        // In order for this line to always return a User you need to add this URL path
        // to the SecurityConfiguration class
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // If you ever find the "Detached entity error" the solution is to go for the user to
        // the database using the repository -> usersRepository.findOne(owner.getId())
        // instead of using the object directly
        ad.setOwner(usersRepository.findOne(owner.getId()));
        adsService.save(ad);
        return "redirect:/ads";
    }

    @ResponseBody
    @GetMapping("/tests")
    public String test() {
        User user = new User("zach", "codeup", "example.@mail.com");
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
