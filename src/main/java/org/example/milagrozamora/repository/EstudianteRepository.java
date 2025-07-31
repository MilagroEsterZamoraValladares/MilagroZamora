package org.example.milagrozamora.repository;

import org.example.milagrozamora.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByDni(String dni);

    @Query("SELECT e FROM Estudiante e ORDER BY e.edad DESC")
    List<Estudiante> findAllOrderByEdadDesc();

    boolean existsByDni(String dni);
}
