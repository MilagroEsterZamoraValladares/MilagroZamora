package org.example.milagrozamora.controller;
import org.example.milagrozamora.dto.CursoDTO;
import org.example.milagrozamora.dto.CursoEstudiantesDTO;
import org.example.milagrozamora.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CursoController {
    private final CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        List<CursoDTO> cursos = cursoService.findAll();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Integer id) {
        return cursoService.findById(id)
                .map(curso -> ResponseEntity.ok(curso))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@Valid @RequestBody CursoDTO cursoDTO) {
        CursoDTO savedCurso = cursoService.save(cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurso);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(
            @PathVariable Integer id,
            @Valid @RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO updatedCurso = cursoService.update(id, cursoDTO);
            return ResponseEntity.ok(updatedCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        try {
            cursoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Endpoint para mostrar relación cursos-estudiantes usando Map
    @GetMapping("/con-estudiantes")
    public ResponseEntity<Map<String, List<String>>> getCursosConEstudiantes() {
        Map<String, List<String>> cursosConEstudiantes = cursoService.getCursosConEstudiantes();
        return ResponseEntity.ok(cursosConEstudiantes);
    }

    // Endpoint alternativo que retorna lista de DTOs
    @GetMapping("/con-estudiantes-list")
    public ResponseEntity<List<CursoEstudiantesDTO>> getCursosConEstudiantesList() {
        List<CursoEstudiantesDTO> cursosConEstudiantes = cursoService.getCursosConEstudiantesList();
        return ResponseEntity.ok(cursosConEstudiantes);
    }
}
