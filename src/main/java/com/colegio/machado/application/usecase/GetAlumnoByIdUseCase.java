
package com.colegio.machado.application.usecase;

import com.colegio.machado.domain.model.Alumno;

public interface GetAlumnoByIdUseCase {
    Alumno execute(Long idAlumno);
}

