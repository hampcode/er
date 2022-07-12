package com.example.ex.controllers;

import com.example.ex.entities.Post;
import com.example.ex.exception.ResourceNotFoundException;
import com.example.ex.repository.PostRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Api
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @ApiOperation(value = "Obtener todos los posts", notes = "Operación obtener todos los posts.")
    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll( );
    }

    @ApiOperation(value = "Obtener todos un post por postId", notes = "Operación obtener un post por postId")
    @GetMapping("/posts/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    @ApiOperation(value = "Crear un nuevo posts", notes = "Operación crear un nuevo posts.")
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }



}