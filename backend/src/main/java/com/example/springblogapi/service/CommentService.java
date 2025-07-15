package com.example.springblogapi.service;

import com.example.springblogapi.dto.CommentRequestDTO;
import com.example.springblogapi.dto.CommentResponseDTO;
import com.example.springblogapi.entity.Comment;
import com.example.springblogapi.entity.Post;
import com.example.springblogapi.entity.User;
import com.example.springblogapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final DTOMapperService dtoMapperService;

    @Transactional
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO){
        User user = userService.getUserEntityById(commentRequestDTO.getUserId());
        Post post = postService.getPostEntityByID(commentRequestDTO.getPostId());
        Comment comment = dtoMapperService.mapToCommentEntity(commentRequestDTO, post, user);
        Comment saved = commentRepository.save(comment);
        return dtoMapperService.mapToCommentResponseDTO(saved);
    }

    public List<CommentResponseDTO> getCommentsByPostId(Long postId){
        Post post = postService.getPostEntityByID(postId);
        return commentRepository.findByPost(post).stream().map(dtoMapperService::mapToCommentResponseDTO).collect(Collectors.toList());
    }
}
