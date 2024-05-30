package com.socialnetwork.post_service.controller;

import com.socialnetwork.post_service.entity.Content;
import com.socialnetwork.post_service.entity.React;
import com.socialnetwork.post_service.service.ReactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/react")
@RequiredArgsConstructor
public class ReactController {
    private final ReactService reactService;
    private static final Logger logger = LoggerFactory.getLogger(ReactController.class);
    @GetMapping
    public ResponseEntity<List<React>> getAllReacts() {
        logger.info("Received request to list all reacts");
        return ResponseEntity.ok(reactService.getAllReacts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<React> getReactById(@PathVariable("id") Integer reactId) {
        logger.info("Received request to fetch react with id: {}", reactId);
        return ResponseEntity.ok(reactService.getReactById(reactId));
    }
    @PostMapping
    public ResponseEntity<React> createReact(@Valid @RequestBody React request) {
        logger.info("Received request to create a new react");
        React createdReact = reactService.createReact(request);
        return new ResponseEntity<>(createdReact, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<React> updateReact(
            @PathVariable("id") Integer reactId, @Valid @RequestBody React request) {
        logger.info("Received request to update react with id: {}", reactId);
        React updatedReact = reactService.updateReact(reactId, request);
        return ResponseEntity.ok(updatedReact);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReact(@PathVariable("id") Integer reactId) {
        logger.info("Received request to delete react with id: {}", reactId);
        reactService.deleteReact(reactId);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/post/{postId}")
    public List<React> getAllReactsByPost(
            @PathVariable("postId") Integer postId
    ) {
        return reactService.getAllReactsByPost(postId);
    }
}
