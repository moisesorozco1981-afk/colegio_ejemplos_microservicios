
package com.colegio.machado.application.usecase;

import com.colegio.machado.domain.model.Alumno;

public interface UpdateAlumnoUseCase {
    Alumno execute(Long id, Alumno alumno, Long idClase );
}

