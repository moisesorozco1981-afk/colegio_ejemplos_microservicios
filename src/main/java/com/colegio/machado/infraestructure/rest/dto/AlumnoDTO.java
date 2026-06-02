package com.colegio.machado.infraestructure.rest.dto;

public record AlumnoDTO(Long id, String nombre, String apellido, Integer edad, SimpleClaseDTO clase){}
