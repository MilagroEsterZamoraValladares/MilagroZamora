package org.example.milagrozamora.controller;
import org.example.milagrozamora.dto.EstudianteDTO;
import org.example.milagrozamora.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EstudianteController {
    private final EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        List<EstudianteDTO> estudiantes = estudianteService.findAll();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable Integer id) {
        return estudianteService.findById(id)
                .map(estudiante -> ResponseEntity.ok(estudiante))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<EstudianteDTO> createEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        try {
            EstudianteDTO savedEstudiante = estudianteService.save(estudianteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEstudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> updateEstudiante(
            @PathVariable Integer id,
            @Valid @RequestBody EstudianteDTO estudianteDTO) {
        try {
            EstudianteDTO updatedEstudiante = estudianteService.update(id, estudianteDTO);
            return ResponseEntity.ok(updatedEstudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Integer id) {
        try {
            estudianteService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para listar estudiantes ordenados por edad descendente
    @GetMapping("/ordenados-por-edad")
    public ResponseEntity<List<EstudianteDTO>> getEstudiantesOrdenadosPorEdad() {
        List<EstudianteDTO> estudiantes = estudianteService.findAllOrderByEdadDesc();
        return ResponseEntity.ok(estudiantes);
    }
}
