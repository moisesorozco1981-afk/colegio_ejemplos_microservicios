package com.colegio.machado.infraestructure.database;

import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.domain.model.AlumnoRepository;
import com.colegio.machado.infraestructure.database.entities.AlumnoEntity;
import com.colegio.machado.infraestructure.database.mappers.AlumnoEntityMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class AlumnosPersistenceAdapter implements AlumnoRepository {

    private final AlumnosRepository alumnosRepository;

    private final AlumnoEntityMapper alumnoMapper;

    public List<Alumno> findAll() {
        List<AlumnoEntity> alumnosEntity = alumnosRepository.findAll();
        return alumnoMapper.mapModel(alumnosEntity);
    }
}
