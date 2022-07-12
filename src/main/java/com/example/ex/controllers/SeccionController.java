package com.example.ex.controllers;

import com.example.ex.entities.Comment;
import com.example.ex.entities.Seccion;
import com.example.ex.exception.ResourceNotFoundException;
import com.example.ex.repository.CommentRepository;
import com.example.ex.repository.CursoRepository;
import com.example.ex.repository.PostRepository;
import com.example.ex.repository.SeccionRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Api
@RestController
public class SeccionController {

    @Autowired
    private SeccionRepository seccionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/cursos/{cursoId}/secciones")
    public List<Seccion> getAllSeccionesByCursoId(@PathVariable (value = "cursoId") Long cursoId) {
        return seccionRepository.findByCursoId(cursoId);
    }

    @PostMapping("/cursos/{cursoId}/secciones")
    public Seccion createSeccion(@PathVariable (value = "cursoId") Long cursoId,
                                 @Valid @RequestBody Seccion seccion) {
        return cursoRepository.findById(cursoId).map(curso -> {
            seccion.setCurso(curso);
            return seccionRepository.save(seccion);
        }).orElseThrow(() -> new ResourceNotFoundException("CursoId " + cursoId + " not found"));
    }


}