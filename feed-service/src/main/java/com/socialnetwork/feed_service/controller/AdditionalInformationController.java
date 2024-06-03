package com.socialnetwork.feed_service.controller;

import com.socialnetwork.feed_service.model.FullPostContent;
import com.socialnetwork.feed_service.model.Post;
import com.socialnetwork.feed_service.model.React;
import com.socialnetwork.feed_service.service.AdditionalInformationService;
import com.socialnetwork.feed_service.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/additional")
@RequiredArgsConstructor
public class AdditionalInformationController {
    private final AdditionalInformationService additionalInformationService;
    private static final Logger logger = LoggerFactory.getLogger(AdditionalInformationController.class);
    @GetMapping("/post/{postId}")
    public ResponseEntity<Integer> getTotalComments(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(additionalInformationService.getTotalComments(postId));
    }
    @GetMapping("/sharepost/{sharepostId}")
    public ResponseEntity<FullPostContent> getSharePostInformation(@PathVariable("sharepostId") Integer sharepostId) {
        return ResponseEntity.ok(additionalInformationService.getSharePostInformation(sharepostId));
    }
}
