package org.example.milagrozamora.service;
import org.example.milagrozamora.dto.CursoDTO;
import org.example.milagrozamora.dto.CursoEstudiantesDTO;
import org.example.milagrozamora.entity.Curso;
import org.example.milagrozamora.entity.Estudiante;
import org.example.milagrozamora.mapper.CursoMapper;
import org.example.milagrozamora.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public List<CursoDTO> findAll() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CursoDTO> findById(Integer id) {
        return cursoRepository.findById(id)
                .map(cursoMapper::toDTO);
    }

    public CursoDTO save(CursoDTO cursoDTO) {
        Curso curso = cursoMapper.toEntity(cursoDTO);
        Curso savedCurso = cursoRepository.save(curso);
        return cursoMapper.toDTO(savedCurso);
    }
    public CursoDTO update(Integer id, CursoDTO cursoDTO) {
        return cursoRepository.findById(id)
                .map(existingCurso -> {
                    existingCurso.setNombre(cursoDTO.getNombre());
                    existingCurso.setSiglas(cursoDTO.getSiglas());
                    existingCurso.setEstado(cursoDTO.getEstado());

                    Curso updatedCurso = cursoRepository.save(existingCurso);
                    return cursoMapper.toDTO(updatedCurso);
                })
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
    }

    public void deleteById(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con ID: " + id);
        }
        cursoRepository.deleteById(id);
    }
    // Método con programación funcional para mostrar relación curso-estudiantes usando Map
    public Map<String, List<String>> getCursosConEstudiantes() {
        return cursoRepository.findCursosConEstudiantes()
                .stream()
                .filter(curso -> curso.getEstado())
                .collect(Collectors.toMap(
                        Curso::getNombre,
                        curso -> curso.getEstudiantes()
                                .stream()
                                .map(estudiante -> estudiante.getNombres() + " " + estudiante.getApellidos())
                                .sorted()
                                .collect(Collectors.toList())
                ));
    }
    // Método alternativo que retorna lista de DTOs
    public List<CursoEstudiantesDTO> getCursosConEstudiantesList() {
        return cursoRepository.findCursosConEstudiantes()
                .stream()
                .filter(curso -> curso.getEstado())
                .map(curso -> CursoEstudiantesDTO.builder()
                        .nombreCurso(curso.getNombre())
                        .estudiantes(curso.getEstudiantes()
                                .stream()
                                .map(estudiante -> estudiante.getNombres() + " " + estudiante.getApellidos())
                                .sorted()
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

}
