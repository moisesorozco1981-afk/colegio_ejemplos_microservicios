
package com.colegio.machado.application.usecase;

import com.colegio.machado.domain.model.Alumno;

public interface CreateAlumnoUseCase {
    Alumno execute(Alumno alumno, Long idClase);
}

