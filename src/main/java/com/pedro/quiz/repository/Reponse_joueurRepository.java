package com.pedro.quiz.repository;

import com.pedro.quiz.domain.Reponse_joueur;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Reponse_joueur entity.
 */
public interface Reponse_joueurRepository extends JpaRepository<Reponse_joueur,Long> {

    @Query("select reponse_joueur from Reponse_joueur reponse_joueur where reponse_joueur.user.login = ?#{principal.username}")
    List<Reponse_joueur> findByUserIsCurrentUser();

}
