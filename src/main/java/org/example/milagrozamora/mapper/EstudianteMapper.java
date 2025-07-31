package org.example.milagrozamora.mapper;
import org.example.milagrozamora.dto.EstudianteDTO;
import org.example.milagrozamora.entity.Estudiante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstudianteMapper {
    public EstudianteDTO toDTO(Estudiante estudiante) {
        if (estudiante == null) return null;
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombres(estudiante.getNombres())
                .apellidos(estudiante.getApellidos())
                .dni(estudiante.getDni())
                .edad(estudiante.getEdad())
                .build();
    }

    public Estudiante toEntity(EstudianteDTO dto) {
        if (dto == null) return null;

        return Estudiante.builder()
                .id(dto.getId())
                .nombres(dto.getNombres())
                .apellidos(dto.getApellidos())
                .dni(dto.getDni())
                .edad(dto.getEdad())
                .build();
    }
    public List<EstudianteDTO> toDTOList(List<Estudiante> estudiantes) {
        return estudiantes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
