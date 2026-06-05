package com.colegio.machado.infraestructure.database.mappers;

import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.database.entities.AlumnoEntity;
import com.colegio.machado.infraestructure.database.entities.ClaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnoEntityMapper {

    Alumno toModel(AlumnoEntity entity);

    AlumnoEntity toEntity(Alumno alumno);


    default Clase map(ClaseEntity entity) {
        if (entity == null) return null;

        return new Clase(
                entity.getId(),
                entity.getNombre(),
                entity.getProfesor()
        );
    }

    default ClaseEntity map(Clase clase) {
        if (clase == null) return null;

        ClaseEntity entity = new ClaseEntity();
        entity.setId(clase.getId());
        entity.setNombre(clase.getNombre());
        entity.setProfesor(clase.getProfesor());
        return entity;
    }

    List<AlumnoEntity> mapEntity(List<Alumno> alumnos);

    List<Alumno> mapModel(List<AlumnoEntity> alumnosEntity);
}
