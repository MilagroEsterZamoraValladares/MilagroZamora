package org.example.milagrozamora;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private Integer id;

    @NotBlank(message = "El nombre del curso es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "Las siglas son obligatorias")
    @Size(max = 10)
    private String siglas;

    private Boolean estado;
    private List<DetalleMatriculaDTO> detalleMatricula;
}
