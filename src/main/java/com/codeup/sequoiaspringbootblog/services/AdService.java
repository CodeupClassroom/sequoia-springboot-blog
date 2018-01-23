package com.codeup.sequoiaspringbootblog.services;

import com.codeup.sequoiaspringbootblog.daos.AdRepository;
import com.codeup.sequoiaspringbootblog.models.Ad;
import org.springframework.stereotype.Service;

@Service
public class AdService {
    private AdRepository dao;

    public AdService(AdRepository dao) {
        this.dao = dao;
    }

    public Iterable<Ad> findAll() {
        return dao.findAll();
    }

    public void save(Ad ad) {
        dao.save(ad);
    }

    public Ad findOne(long id) {
        return dao.findOne(id);
    }
}
