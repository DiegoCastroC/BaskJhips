package com.diego.castro.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.diego.castro.domain.Especificaciones;
import com.diego.castro.repository.EspecificacionesRepository;
import com.diego.castro.web.rest.util.HeaderUtil;
import com.diego.castro.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Especificaciones.
 */
@RestController
@RequestMapping("/api")
public class EspecificacionesResource {

    private final Logger log = LoggerFactory.getLogger(EspecificacionesResource.class);

    @Inject
    private EspecificacionesRepository especificacionesRepository;

    /**
     * POST  /especificacioness -> Create a new especificaciones.
     */
    @RequestMapping(value = "/especificacioness",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Especificaciones> create(@Valid @RequestBody Especificaciones especificaciones) throws URISyntaxException {
        log.debug("REST request to save Especificaciones : {}", especificaciones);
        if (especificaciones.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new especificaciones cannot already have an ID").body(null);
        }
        Especificaciones result = especificacionesRepository.save(especificaciones);
        return ResponseEntity.created(new URI("/api/especificacioness/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("especificaciones", result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /especificacioness -> Updates an existing especificaciones.
     */
    @RequestMapping(value = "/especificacioness",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Especificaciones> update(@Valid @RequestBody Especificaciones especificaciones) throws URISyntaxException {
        log.debug("REST request to update Especificaciones : {}", especificaciones);
        if (especificaciones.getId() == null) {
            return create(especificaciones);
        }
        Especificaciones result = especificacionesRepository.save(especificaciones);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("especificaciones", especificaciones.getId().toString()))
                .body(result);
    }

    /**
     * GET  /especificacioness -> get all the especificacioness.
     */
    @RequestMapping(value = "/especificacioness",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Especificaciones>> getAll(@RequestParam(value = "page" , required = false) Integer offset,
                                  @RequestParam(value = "per_page", required = false) Integer limit)
        throws URISyntaxException {
        Page<Especificaciones> page = especificacionesRepository.findAll(PaginationUtil.generatePageRequest(offset, limit));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/especificacioness", offset, limit);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /especificacioness/:id -> get the "id" especificaciones.
     */
    @RequestMapping(value = "/especificacioness/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Especificaciones> get(@PathVariable Long id) {
        log.debug("REST request to get Especificaciones : {}", id);
        return Optional.ofNullable(especificacionesRepository.findOne(id))
            .map(especificaciones -> new ResponseEntity<>(
                especificaciones,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /especificacioness/:id -> delete the "id" especificaciones.
     */
    @RequestMapping(value = "/especificacioness/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Especificaciones : {}", id);
        especificacionesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("especificaciones", id.toString())).build();
    }
}
