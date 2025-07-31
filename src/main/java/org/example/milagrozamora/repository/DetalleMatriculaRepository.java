package org.example.milagrozamora.repository;

import  org.example.milagrozamora.entity.DetalleMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleMatriculaRepository  extends JpaRepository<DetalleMatricula, Integer> {
    List<DetalleMatricula> findByCursoId(Integer cursoId);
}
