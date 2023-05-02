package com.example.blogging.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.blogging.models.Post;
import com.example.blogging.models.User;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByAuthor(User author);
    List<Post> findByAuthor_Username(String username);
}
