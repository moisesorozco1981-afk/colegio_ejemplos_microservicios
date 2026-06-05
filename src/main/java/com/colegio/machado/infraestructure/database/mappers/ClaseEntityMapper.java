package com.colegio.machado.infraestructure.database.mappers;

import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.database.entities.ClaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaseEntityMapper {

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "profesor", target = "profesor")
    ClaseEntity toEntity(Clase clase);

    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "profesor", target = "profesor")
    Clase toModel(ClaseEntity claseEntity);

    List<ClaseEntity> mapEntity(List<Clase> clase);

    List<Clase> mapModel(List<ClaseEntity> claseEntity);
}
