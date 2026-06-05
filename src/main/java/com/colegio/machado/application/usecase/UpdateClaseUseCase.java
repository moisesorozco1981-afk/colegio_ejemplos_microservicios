
package com.colegio.machado.application.usecase;

import com.colegio.machado.domain.model.Clase;

public interface UpdateClaseUseCase {
    Clase execute(Long id, Clase clase );
}

