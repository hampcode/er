package com.example.ex.repository;



import com.example.ex.entities.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Long> {

    List<Seccion> findByCursoId(Long cursoId);
    Optional<Seccion> findByIdAndCursoId(Long id, Long cursoId);
}