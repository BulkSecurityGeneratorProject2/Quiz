package com.pedro.quiz.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pedro.quiz.domain.Reponse_joueur;
import com.pedro.quiz.repository.Reponse_joueurRepository;
import com.pedro.quiz.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Reponse_joueur.
 */
@RestController
@RequestMapping("/api")
public class Reponse_joueurResource {

    private final Logger log = LoggerFactory.getLogger(Reponse_joueurResource.class);

    @Inject
    private Reponse_joueurRepository reponse_joueurRepository;

    /**
     * POST  /reponse_joueurs -> Create a new reponse_joueur.
     */
    @RequestMapping(value = "/reponse_joueurs",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Reponse_joueur> createReponse_joueur(@RequestBody Reponse_joueur reponse_joueur) throws URISyntaxException {
        log.debug("REST request to save Reponse_joueur : {}", reponse_joueur);
        if (reponse_joueur.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new reponse_joueur cannot already have an ID").body(null);
        }
        Reponse_joueur result = reponse_joueurRepository.save(reponse_joueur);
        return ResponseEntity.created(new URI("/api/reponse_joueurs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("reponse_joueur", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /reponse_joueurs -> Updates an existing reponse_joueur.
     */
    @RequestMapping(value = "/reponse_joueurs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Reponse_joueur> updateReponse_joueur(@RequestBody Reponse_joueur reponse_joueur) throws URISyntaxException {
        log.debug("REST request to update Reponse_joueur : {}", reponse_joueur);
        if (reponse_joueur.getId() == null) {
            return createReponse_joueur(reponse_joueur);
        }
        Reponse_joueur result = reponse_joueurRepository.save(reponse_joueur);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("reponse_joueur", reponse_joueur.getId().toString()))
                .body(result);
    }

    /**
     * GET  /reponse_joueurs -> get all the reponse_joueurs.
     */
    @RequestMapping(value = "/reponse_joueurs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Reponse_joueur> getAllReponse_joueurs() {
        log.debug("REST request to get all Reponse_joueurs");
        return reponse_joueurRepository.findAll();
    }

    /**
     * GET  /reponse_joueurs/:id -> get the "id" reponse_joueur.
     */
    @RequestMapping(value = "/reponse_joueurs/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Reponse_joueur> getReponse_joueur(@PathVariable Long id) {
        log.debug("REST request to get Reponse_joueur : {}", id);
        return Optional.ofNullable(reponse_joueurRepository.findOne(id))
            .map(reponse_joueur -> new ResponseEntity<>(
                reponse_joueur,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /reponse_joueurs/:id -> delete the "id" reponse_joueur.
     */
    @RequestMapping(value = "/reponse_joueurs/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteReponse_joueur(@PathVariable Long id) {
        log.debug("REST request to delete Reponse_joueur : {}", id);
        reponse_joueurRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("reponse_joueur", id.toString())).build();
    }
}
