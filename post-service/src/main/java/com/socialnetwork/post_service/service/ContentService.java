package com.socialnetwork.post_service.service;

import com.socialnetwork.post_service.entity.Content;
import com.socialnetwork.post_service.repository.ContentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final ContentRepository contentRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContentService.class);

    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    public Content getContentById(Integer contentId) throws EntityNotFoundException {
        return contentRepository.findById(contentId)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + contentId));
    }

    @Transactional
    public Content createContent(Content request) {
        logger.info("Creating new content by postId {}", request.getPostId());
        var content = Content.builder()
                .textContent(request.getTextContent())
                .linkContent(request.getLinkContent())
                .type(request.getType())
                .postId(request.getPostId())
                .build();
        return contentRepository.save(content);
    }

    @Transactional
    public Content updateContent(Integer contentId, Content request) throws EntityNotFoundException {
        logger.info("Updating content with id {}", contentId);
        Content contentUpdate = contentRepository.findById(contentId)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + contentId));

        contentUpdate.setTextContent(request.getTextContent());
        contentUpdate.setLinkContent(request.getLinkContent());

        return contentRepository.save(contentUpdate);
    }

    @Transactional
    public void deleteContent(Integer contentId) {
        logger.info("Deleting content with id {}", contentId);
        contentRepository.deleteById(contentId);
    }
}
