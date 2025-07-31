package org.example.milagrozamora.dto;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoEstudiantesDTO {
    private String nombreCurso;
    private List<String> estudiantes;
}
