package com.leffycruze.doodle.service;

import com.leffycruze.doodle.entity.Doodle;
import com.leffycruze.doodle.entity.Proposition;
import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.repository.DoodleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoodleService {

    @Autowired
    private DoodleRepository repository;

    public Doodle create(String title, String place, List<Proposition> propositions, User organizer) {
        Doodle doodle = new Doodle(title, place, propositions, organizer);
        return repository.save(doodle);
    }
}