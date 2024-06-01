package com.socialnetwork.comment_service.service;

import com.socialnetwork.comment_service.entity.Comment;
import com.socialnetwork.comment_service.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Integer commentId) throws EntityNotFoundException {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));
    }

    @Transactional
    public Comment createComment(Comment request) {
        logger.info("Creating new comment by userId {} in postId {}", request.getUserId(), request.getPostId());
        var comment = Comment.builder()
                .comment(request.getComment())
                .userId(request.getUserId())
                .postId(request.getPostId())
                .build();
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Integer commentId, Comment request) throws EntityNotFoundException {
        logger.info("Updating comment with id {}", commentId);
        Comment commentUpdate = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

        commentUpdate.setComment(request.getComment());

        return commentRepository.save(commentUpdate);
    }

    @Transactional
    public void deleteComment(Integer commentId) {
        logger.info("Deleting comment with id {}", commentId);
        commentRepository.deleteById(commentId);
    }

    public List<Comment> getAllCommentsByPost(Integer postId) {
        return commentRepository.findByPostIdOrderByCreatedTimeDesc(postId);
    }
}
