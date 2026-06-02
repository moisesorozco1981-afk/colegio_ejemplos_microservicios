package com.colegio.machado.application;

import com.colegio.machado.application.exceptions.NotFoundException;
import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.database.ClaseRepository;
import com.colegio.machado.infraestructure.database.entities.ClaseEntity;
import com.colegio.machado.infraestructure.database.mappers.ClaseEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasesService {

    @Autowired
    ClaseRepository claseRepository;
    @Autowired
    ClaseEntityMapper claseMapper;

    public List<Clase> getAllClases() {
        List<ClaseEntity> claseEntity = claseRepository.findAll();
        if (claseEntity.isEmpty()) {
            throw new NotFoundException("No se encontro el clase");
        }
        List<Clase> clases = claseMapper.mapModel(claseEntity);

        return clases;
    }


    public Clase getClaseById(Long id) {
        ClaseEntity claseEntity = claseRepository.findByIdWithAlumnos(id);

        if (claseEntity == null) {
            throw new RuntimeException("Clase no encontrada");
        }

        return claseMapper.toModel(claseEntity);
    }


    public Clase guardarClase(Clase clase) {

        // Modelo → Entity
        ClaseEntity entity = claseMapper.toEntity(clase);

        // Guardar
        ClaseEntity guardada = claseRepository.save(entity);

        // Entity → Modelo
        return claseMapper.toModel(guardada);
    }

    public Clase actualizarClase(Long id, Clase clase) {
        ClaseEntity entity = claseRepository.findById(id).orElseThrow(() -> new NotFoundException("Clase no encontrada"));
        entity.setClase(clase.getClase());
        entity.setProfesor(clase.getProfesor());
        ClaseEntity actualizada = claseRepository.save(entity);

        return claseMapper.toModel(actualizada);
    }

    public void eliminarClase(Long id) {
        ClaseEntity entity = claseRepository.findById(id).orElseThrow(() -> new NotFoundException("Clase no encontrada"));
        claseRepository.delete(entity);
    }

}
