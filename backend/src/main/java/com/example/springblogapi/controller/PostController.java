package com.example.springblogapi.controller;

import com.example.springblogapi.dto.PostRequestDTO;
import com.example.springblogapi.dto.PostResponseDTO;
import com.example.springblogapi.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    Logger logger = Logger.getLogger(PostController.class.getName());
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDTO createPost(@Valid @RequestBody PostRequestDTO postRequestDTO){
        logger.info(postRequestDTO.toString());
        return postService.createPost(postRequestDTO);
    }

    @GetMapping("/{id}")
    public PostResponseDTO getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @GetMapping
    public List<PostResponseDTO> getAllPosts(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size){
        return postService.getAllPosts(page,size);
    }

    @GetMapping("/user/{userId}")
    public List<PostResponseDTO> getPostsByUser(@PathVariable Long userId,
                                                @RequestParam(defaultValue = "0")int page,
                                                @RequestParam(defaultValue = "10")int size
    ) {
        return postService.getPostsByUser(userId, page, size);
    }
}
