package com.socialnetwork.post_service.controller;

import com.socialnetwork.post_service.entity.Content;
import com.socialnetwork.post_service.service.ContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/content")
@RequiredArgsConstructor
public class ContentController {
    private final ContentService contentService;
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
    @GetMapping
    public ResponseEntity<List<Content>> getAllContents() {
        logger.info("Received request to list all contents");
        return ResponseEntity.ok(contentService.getAllContents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable("id") Integer contentId) {
        logger.info("Received request to fetch content with id: {}", contentId);
        return ResponseEntity.ok(contentService.getContentById(contentId));
    }
    @PostMapping
    public ResponseEntity<Content> createContent(@Valid @RequestBody Content request) {
        logger.info("Received request to create a new content");
        Content createdContent = contentService.createContent(request);
        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(
            @PathVariable("id") Integer contentId, @Valid @RequestBody Content request) {
        logger.info("Received request to update content with id: {}", contentId);
        Content updatedContent = contentService.updateContent(contentId, request);
        return ResponseEntity.ok(updatedContent);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable("id") Integer contentId) {
        logger.info("Received request to delete content with id: {}", contentId);
        contentService.deleteContent(contentId);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/post/{postId}")
    public List<Content> getAllContentsByPost(
            @PathVariable("postId") Integer postId
    ) {
        return contentService.getAllContentsByPost(postId);
    }
}
