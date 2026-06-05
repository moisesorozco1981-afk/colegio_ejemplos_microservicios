package com.colegio.machado.domain.ports.out;

import com.colegio.machado.domain.model.Clase;

import java.util.List;

public interface ClaseRepository {
    List<Clase> findAll();
    Clase findById(Long id);
    Clase save(Clase clase);
    void delete(Long id);

}
