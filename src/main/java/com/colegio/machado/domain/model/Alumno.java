package com.colegio.machado.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    private Long id;

    /**
     * Nombre del Alumno
     */
    private String nombre;

    private String apellido;

    private Integer edad;

    private Clase clase;

}
