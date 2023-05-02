package com.example.blogging.controllers;

import com.example.blogging.dto.PostDto;
import com.example.blogging.dto.UserDto;
import com.example.blogging.services.PostService;
import com.example.blogging.services.UserService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class ApiController {

    @Autowired private UserService userService;

    @Autowired private PostService postService;

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        System.out.println(createdUser.toString());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable Long id) {
        UserDto user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto>
    createPost(@RequestParam("file") MultipartFile file,
               @RequestParam("title") String title,
               @RequestParam("subtitle") String subtitle,
               @RequestParam("username") String username) throws IOException {
        PostDto createdPost =
            postService.createPost(file, title, subtitle, username);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() throws IOException {
        List<PostDto> postDtos = postService.getAllPosts();
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/posts/user/{username}")
    public List<PostDto> getAllPostsByUser(@PathVariable String username)
        throws IOException {
        List<PostDto> posts = postService.getAllPostsByUser(username);
        return posts;
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id)
        throws IOException {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDto>
    updatePostById(@PathVariable Long id,
                   @RequestParam("file") MultipartFile file,
                   @RequestParam("title") String title,
                   @RequestParam("subtitle") String subtitle)
        throws IOException {
        PostDto updatedPost =
            postService.updatePostById(id, file, title, subtitle);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) throws IOException {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
