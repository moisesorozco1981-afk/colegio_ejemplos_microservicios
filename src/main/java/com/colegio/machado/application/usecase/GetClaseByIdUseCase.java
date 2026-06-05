
package com.colegio.machado.application.usecase;

import com.colegio.machado.domain.model.Clase;

public interface GetClaseByIdUseCase {
    Clase execute(Long idClase);
}

