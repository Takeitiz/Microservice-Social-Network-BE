package com.socialnetwork.post_service.service;

import com.socialnetwork.post_service.entity.Content;
import com.socialnetwork.post_service.entity.React;
import com.socialnetwork.post_service.repository.ReactRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReactService {
    private final ReactRepository reactRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReactService.class);

    public List<React> getAllReacts() {
        return reactRepository.findAll();
    }

    public React getReactById(Integer reactId) throws EntityNotFoundException {
        return reactRepository.findById(reactId)
                .orElseThrow(() -> new EntityNotFoundException("React not found with id: " + reactId));
    }

    @Transactional
    public React createReact(React request) {
        logger.info("Creating new react by userId {} in postId {}",request.getUserId(), request.getPostId());
        var react = React.builder()
                .type(request.getType())
                .postId(request.getPostId())
                .userId(request.getUserId())
                .build();
        return reactRepository.save(react);
    }
    @Transactional
    public React updateReact(Integer reactId, React request) throws EntityNotFoundException {
        logger.info("Updating react with id {}", reactId);
        React reactUpdate = reactRepository.findById(reactId)
                .orElseThrow(() -> new EntityNotFoundException("React not found with id: " + reactId));

        reactUpdate.setType(request.getType());

        return reactRepository.save(reactUpdate);
    }

    @Transactional
    public void deleteReact(Integer reactId) {
        logger.info("Deleting react with id {}", reactId);
        reactRepository.deleteById(reactId);
    }

    public List<React> getAllReactsByPost(Integer postId) {
        return reactRepository.findByPostId(postId);
    }
}
