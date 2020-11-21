package com.leffycruze.doodle.repository;

import com.leffycruze.doodle.entity.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropositionRepository extends JpaRepository<Proposition, Integer> {
}
