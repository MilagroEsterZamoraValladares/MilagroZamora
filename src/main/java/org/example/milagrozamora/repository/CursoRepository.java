package org.example.milagrozamora.repository;
import org.example.milagrozamora.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{
    List<Curso> findByEstadoTrue();

    @Query("SELECT c FROM Curso c LEFT JOIN FETCH c.estudiantes WHERE c.estado = true")
    List<Curso> findCursosConEstudiantes();
}
