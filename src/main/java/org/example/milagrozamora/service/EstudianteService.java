package org.example.milagrozamora.service;
import org.example.milagrozamora.dto.EstudianteDTO;
import org.example.milagrozamora.entity.Estudiante;
import org.example.milagrozamora.mapper.EstudianteMapper;
import org.example.milagrozamora.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;

    public List<EstudianteDTO> findAll() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudianteMapper.toDTOList(estudiantes);
    }

    public Optional<EstudianteDTO> findById(Integer id) {
        return estudianteRepository.findById(id)
                .map(estudianteMapper::toDTO);
    }

    public EstudianteDTO save(EstudianteDTO estudianteDTO) {
        if (estudianteRepository.existsByDni(estudianteDTO.getDni())) {
            throw new RuntimeException("Ya existe un estudiante con el DNI: " + estudianteDTO.getDni());
        }

        Estudiante estudiante = estudianteMapper.toEntity(estudianteDTO);
        Estudiante savedEstudiante = estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(savedEstudiante);
    }
    public EstudianteDTO update(Integer id, EstudianteDTO estudianteDTO) {
        return estudianteRepository.findById(id)
                .map(existingEstudiante -> {
                    // Verificar DNI duplicado solo si cambió
                    if (!existingEstudiante.getDni().equals(estudianteDTO.getDni()) &&
                            estudianteRepository.existsByDni(estudianteDTO.getDni())) {
                        throw new RuntimeException("Ya existe un estudiante con el DNI: " + estudianteDTO.getDni());
                    }

                    existingEstudiante.setNombres(estudianteDTO.getNombres());
                    existingEstudiante.setApellidos(estudianteDTO.getApellidos());
                    existingEstudiante.setDni(estudianteDTO.getDni());
                    existingEstudiante.setEdad(estudianteDTO.getEdad());

                    Estudiante updatedEstudiante = estudianteRepository.save(existingEstudiante);
                    return estudianteMapper.toDTO(updatedEstudiante);
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }
    public void deleteById(Integer id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    // Método con programación funcional para listar estudiantes ordenados por edad descendente
    public List<EstudianteDTO> findAllOrderByEdadDesc() {
        return estudianteRepository.findAllOrderByEdadDesc()
                .stream()
                .map(estudianteMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }
}
