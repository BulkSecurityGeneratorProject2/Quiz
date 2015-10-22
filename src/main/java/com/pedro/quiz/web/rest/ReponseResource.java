package com.pedro.quiz.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.pedro.quiz.domain.Reponse;
import com.pedro.quiz.repository.ReponseRepository;
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
 * REST controller for managing Reponse.
 */
@RestController
@RequestMapping("/api")
public class ReponseResource {

    private final Logger log = LoggerFactory.getLogger(ReponseResource.class);

    @Inject
    private ReponseRepository reponseRepository;

    /**
     * POST  /reponses -> Create a new reponse.
     */
    @RequestMapping(value = "/reponses",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Reponse> createReponse(@RequestBody Reponse reponse) throws URISyntaxException {
        log.debug("REST request to save Reponse : {}", reponse);
        if (reponse.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new reponse cannot already have an ID").body(null);
        }
        Reponse result = reponseRepository.save(reponse);
        return ResponseEntity.created(new URI("/api/reponses/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("reponse", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /reponses -> Updates an existing reponse.
     */
    @RequestMapping(value = "/reponses",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Reponse> updateReponse(@RequestBody Reponse reponse) throws URISyntaxException {
        log.debug("REST request to update Reponse : {}", reponse);
        if (reponse.getId() == null) {
            return createReponse(reponse);
        }
        Reponse result = reponseRepository.save(reponse);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("reponse", reponse.getId().toString()))
                .body(result);
    }

    /**
     * GET  /reponses -> get all the reponses.
     */
    @RequestMapping(value = "/reponses",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Reponse> getAllReponses() {
        log.debug("REST request to get all Reponses");
        return reponseRepository.findAll();
    }

    /**
     * GET  /reponses/:id -> get the "id" reponse.
     */
    @RequestMapping(value = "/reponses/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Reponse> getReponse(@PathVariable Long id) {
        log.debug("REST request to get Reponse : {}", id);
        return Optional.ofNullable(reponseRepository.findOne(id))
            .map(reponse -> new ResponseEntity<>(
                reponse,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /reponses/:id -> delete the "id" reponse.
     */
    @RequestMapping(value = "/reponses/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteReponse(@PathVariable Long id) {
        log.debug("REST request to delete Reponse : {}", id);
        reponseRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("reponse", id.toString())).build();
    }
}
