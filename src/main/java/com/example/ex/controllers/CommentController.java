package com.example.ex.controllers;


import com.example.ex.entities.Comment;
import com.example.ex.exception.ResourceNotFoundException;
import com.example.ex.repository.CommentRepository;
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
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @ApiOperation(value = "Obtiene los comentarios por Post Id", notes = "Operación de listado de comentarios por Post Id.")
    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @ApiOperation(value = "Crear un comentario de un Post Id", notes = "Operación de crear comentario por Post Id.")
    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


}