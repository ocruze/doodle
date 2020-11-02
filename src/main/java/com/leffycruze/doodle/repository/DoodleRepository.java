package com.leffycruze.doodle.repository;

import com.leffycruze.doodle.entity.Doodle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoodleRepository extends JpaRepository<Doodle, Integer> {


}
