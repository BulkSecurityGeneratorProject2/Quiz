package com.pedro.quiz.repository;

import com.pedro.quiz.domain.Score;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Score entity.
 */
public interface ScoreRepository extends JpaRepository<Score,Long> {

}
