package com.colegio.machado.infraestructure.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlumnoRequestDTO(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,
        @NotNull(message = "La edad es obligatoria")
        @Min(value = 1, message = "La edad debe ser mayor que 0")
        Integer edad,
        Long clase) {
}
