package com.wodtrack.training.athlete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete,Long> {

    boolean existsByEmail(String email);
}
