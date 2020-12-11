package com.leffycruze.doodle.repository;

import com.leffycruze.doodle.entity.Doodle;
import com.leffycruze.doodle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoodleRepository extends JpaRepository<Doodle, Integer> {

    Optional<List<Doodle>> findAllByOrganizer(User user);
}
