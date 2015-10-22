package com.pedro.quiz.repository;

import com.pedro.quiz.domain.Reponse;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Reponse entity.
 */
public interface ReponseRepository extends JpaRepository<Reponse,Long> {

}
