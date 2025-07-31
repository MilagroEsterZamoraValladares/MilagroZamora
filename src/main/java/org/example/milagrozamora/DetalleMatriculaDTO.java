package org.example.milagrozamora;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMatriculaDTO {
    private Integer id;
    private Integer cursoId;

    @NotBlank(message = "El aula es obligatoria")
    @Size(max = 20)
    private String aula;
}
