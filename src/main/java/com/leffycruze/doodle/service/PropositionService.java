package com.leffycruze.doodle.service;

import com.leffycruze.doodle.entity.Proposition;
import com.leffycruze.doodle.entity.User;
import com.leffycruze.doodle.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PropositionService {

    @Autowired
    private PropositionRepository propositionRepository;

    public void voteGoing(Integer idProp, User user) {
        Proposition prop = propositionRepository.getOne(idProp);
        prop.getGoing().add(user);
    }

    public void voteNotGoing(Integer idProp, User user) {
        Proposition prop = propositionRepository.getOne(idProp);
        prop.getNotGoing().add(user);
    }

    public void voteMaybe(Integer idProp, User user) {
        Proposition prop = propositionRepository.getOne(idProp);
        prop.getMaybe().add(user);
    }

    public void voteDelete(Integer idProp, User user) {
        Proposition prop = propositionRepository.getOne(idProp);
        prop.getGoing().remove(user);
        prop.getNotGoing().remove(user);
        prop.getMaybe().remove(user);
    }
}
