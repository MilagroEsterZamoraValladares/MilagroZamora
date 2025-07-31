package org.example.milagrozamora.mapper;

import org.example.milagrozamora.dto.CursoDTO;
import org.example.milagrozamora.dto.DetalleMatriculaDTO;
import org.example.milagrozamora.entity.Curso;
import org.example.milagrozamora.entity.DetalleMatricula;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CursoMapper {
    public CursoDTO toDTO(Curso curso) {
        if (curso == null) return null;

        List<DetalleMatriculaDTO> detalles = null;
        if (curso.getDetalleMatricula() != null) {
            detalles = curso.getDetalleMatricula().stream()
                    .map(this::detalleToDTO)
                    .collect(Collectors.toList());
        }

        return CursoDTO.builder()
                .id(curso.getId())
                .nombre(curso.getNombre())
                .siglas(curso.getSiglas())
                .estado(curso.getEstado())
                .detalleMatricula(detalles)
                .build();
    }
    public Curso toEntity(CursoDTO dto) {
        if (dto == null) return null;

        return Curso.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .siglas(dto.getSiglas())
                .estado(dto.getEstado() != null ? dto.getEstado() : true)
                .build();
    }

    private DetalleMatriculaDTO detalleToDTO(DetalleMatricula detalle) {
        return DetalleMatriculaDTO.builder()
                .id(detalle.getId())
                .cursoId(detalle.getCurso().getId())
                .aula(detalle.getAula())
                .build();
    }
}
