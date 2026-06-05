package com.colegio.machado.domain.ports.out;

import com.colegio.machado.domain.model.Alumno;

import java.util.List;

public interface AlumnoRepository {
    List<Alumno> findAll();
    Alumno findById(Long id);
    Alumno save(Alumno alumno);
    void delete(Long id);
}
