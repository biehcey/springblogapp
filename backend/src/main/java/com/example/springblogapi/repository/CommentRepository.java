package com.example.springblogapi.repository;

import com.example.springblogapi.entity.Comment;
import com.example.springblogapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);
}
