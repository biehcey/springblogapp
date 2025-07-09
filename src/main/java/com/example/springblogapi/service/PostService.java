package com.example.springblogapi.service;

import com.example.springblogapi.dto.PostRequestDTO;
import com.example.springblogapi.dto.PostResponseDTO;
import com.example.springblogapi.entity.Post;
import com.example.springblogapi.entity.User;
import com.example.springblogapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final DTOMapperService dtoMapperService;
    @Transactional
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO){
        User user = userService.getUserEntityById(postRequestDTO.getUserId());
        Post post = dtoMapperService.mapToPostEntity(postRequestDTO, user);
        Post savedPost = postRepository.save(post);
        return dtoMapperService.mapToPostResponseDTO(savedPost);
    }

    public PostResponseDTO getPostById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return dtoMapperService.mapToPostResponseDTO(post);
    }

    public List<PostResponseDTO> getAllPosts(int page, int size){
        Page<Post> postPage = postRepository.findAll(PageRequest.of(page,size));
        return postPage.getContent().stream().map(dtoMapperService::mapToPostResponseDTO).collect(Collectors.toList());
    }

    public List<PostResponseDTO> getPostsByUser(Long userId, int page, int size){
        User user = userService.getUserEntityById(userId);
        Page<Post> posts = postRepository.findByUser(user, PageRequest.of(page,size));
        return posts.getContent().stream().map(dtoMapperService::mapToPostResponseDTO).collect(Collectors.toList());
    }
}
