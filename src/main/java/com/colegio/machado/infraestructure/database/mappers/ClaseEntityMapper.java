package com.colegio.machado.infraestructure.database.mappers;

import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.database.entities.ClaseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AlumnoEntityMapper.class})
public interface ClaseEntityMapper {

    ClaseEntity toEntity(Clase clase);

    Clase toModel(ClaseEntity claseEntity);

    List<ClaseEntity> mapEntity(List<Clase> clase);

    List<Clase> mapModel(List<ClaseEntity> claseEntity);
}
