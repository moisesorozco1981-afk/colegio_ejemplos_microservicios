package com.colegio.machado.infraestructure.database;

import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.domain.ports.out.AlumnoRepository;
import com.colegio.machado.infraestructure.database.entities.AlumnoEntity;
import com.colegio.machado.infraestructure.database.mappers.AlumnoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AlumnosPersistenceAdapter implements AlumnoRepository {

    private final AlumnosJpaRepository alumnosRepository;

    private final AlumnoEntityMapper alumnoMapper;

    @Override
    public List<Alumno> findAll() {
        return alumnoMapper.mapModel(alumnosRepository.findAll());
    }
    @Override
    public Alumno findById(Long id) {
        return alumnosRepository.findById(id).map(alumnoMapper::toModel).orElse(null);
    }
    @Override
    public Alumno save(Alumno alumno) {
        AlumnoEntity entity = alumnoMapper.toEntity(alumno);
        AlumnoEntity saved = alumnosRepository.save(entity);
        return alumnoMapper.toModel(saved);
    }
    @Override
    public void delete(Long id) {
        alumnosRepository.deleteById(id);
    }
}
