package com.leffycruze.doodle.service;

import com.leffycruze.doodle.entity.Doodle;
import com.leffycruze.doodle.entity.Proposition;
import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.repository.DoodleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoodleService {

    @Autowired
    private DoodleRepository repository;

    public Optional<Doodle> findById(Integer idDoodle) {
        return repository.findById(idDoodle);
    }

    public Doodle create(String title, String place, List<Proposition> propositions, User organizer) {
        Doodle doodle = new Doodle(title, place, propositions, organizer);
        return repository.save(doodle);
    }

    public List<Doodle> getDoodlesByOrganizer(User organizer) {
        return repository.findAllByOrganizer(organizer).orElse(new ArrayList<>());
    }

    public void delete(Doodle doodle) {
        repository.delete(doodle);
    }

}
