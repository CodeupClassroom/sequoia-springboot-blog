package com.codeup.sequoiaspringbootblog.services;

import com.codeup.sequoiaspringbootblog.models.Ad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {
    private List<Ad> ads;

    public AdService() {
        ads = new ArrayList<>();
        createAds();
    }

    public List<Ad> findAll() {
        return ads;
    }

    public Ad save(Ad ad) {
        ad.setId(ads.size() + 1);
        ads.add(ad);
        return ad;
    }

    public Ad findOne(long id) {
        return ads.get((int)(id - 1));
    }

    private void createAds() {
        save(new Ad("Ad A", "Description A"));
        save(new Ad("Ad B", "Description B"));
        save(new Ad("Ad C", "Description C"));
    }
}
