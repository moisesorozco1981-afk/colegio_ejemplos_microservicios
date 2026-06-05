package com.colegio.machado.infraestructure.database;

import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.domain.ports.out.ClaseRepository;
import com.colegio.machado.infraestructure.database.entities.ClaseEntity;
import com.colegio.machado.infraestructure.database.mappers.ClaseEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClasePersistenceAdapter implements ClaseRepository {

    // JPA repository
    private final ClaseJpaRepository claseRepository;

    private final ClaseEntityMapper claseMapper;

    // Mapper
    @Override
    public List<Clase> findAll() {
        return claseMapper.mapModel(claseRepository.findAll());
    }
    @Override
    public Clase findById(Long id) {
        return claseRepository.findById(id).map(claseMapper::toModel).orElse(null);
    }
    @Override
    public Clase save(Clase clase) {
        ClaseEntity entity = claseMapper.toEntity(clase);
        ClaseEntity saved = claseRepository.save(entity);
        return claseMapper.toModel(saved);
    }
    @Override
    public void delete(Long id) {
        claseRepository.deleteById(id);
    }
}
