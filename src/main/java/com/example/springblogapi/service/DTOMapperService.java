package com.example.springblogapi.service;

import com.example.springblogapi.dto.*;
import com.example.springblogapi.entity.Comment;
import com.example.springblogapi.entity.Post;
import com.example.springblogapi.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DTOMapperService {
    private final ModelMapper modelMapper;

    public User mapToUserEntity(UserRequestDTO userRequestDTO){
        return modelMapper.map(userRequestDTO, User.class);
    }

    public UserResponseDTO mapToUserResponseDTO(User user){
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public Comment mapToCommentEntity(CommentRequestDTO commentRequestDTO, Post post, User user){
        Comment comment = modelMapper.map(commentRequestDTO, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        return comment;
    }

    public CommentResponseDTO mapToCommentResponseDTO(Comment comment){
        CommentResponseDTO commentResponseDTO = modelMapper.map(comment, CommentResponseDTO.class);
        commentResponseDTO.setUsername(comment.getUser().getUsername());
        return  commentResponseDTO;
    }

    public Post mapToPostEntity(PostRequestDTO postRequestDTO, User user){
        Post post = modelMapper.map(postRequestDTO, Post.class);
        post.setId(null);
        post.setUser(user);
        return post;
    }

    public PostResponseDTO mapToPostResponseDTO(Post post){
        PostResponseDTO postResponseDTO = modelMapper.map(post, PostResponseDTO.class);
        postResponseDTO.setUsername(post.getUser().getUsername());
        return postResponseDTO;
    }
}
