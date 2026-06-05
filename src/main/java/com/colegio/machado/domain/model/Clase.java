package com.colegio.machado.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clase {
    private Long id;
    private String nombre;
    private String profesor;
}
