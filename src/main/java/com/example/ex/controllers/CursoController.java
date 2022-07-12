package com.example.ex.controllers;

import com.example.ex.entities.Curso;
import com.example.ex.exception.ResourceNotFoundException;
import com.example.ex.repository.CursoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@Api
@RestController
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping("/cursos")
    public List<Curso> getAllCursos() {
        return cursoRepository.findAll( );
    }


    @GetMapping("/cursos/{cursoId}")
    public Curso getCursoById(@PathVariable Long cursoId) {
        return cursoRepository.findById(cursoId).orElseThrow(() -> new ResourceNotFoundException("CursoId " + cursoId + " not found"));
    }

    @PostMapping("/cursos")
    public Curso createCurso(@Valid @RequestBody Curso curso) {
        return cursoRepository.save(curso);
    }



}