package org.example.milagrozamora;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estudiante {
    private Integer id;
    private String nombres;
    private String apellidos;
    private String dni;
    private Integer edad;
}
