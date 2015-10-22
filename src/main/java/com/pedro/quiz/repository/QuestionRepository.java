package com.pedro.quiz.repository;

import com.pedro.quiz.domain.Question;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Question entity.
 */
public interface QuestionRepository extends JpaRepository<Question,Long> {

}
