package com.colegio.machado.infraestructure.rest.mappers;


import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.rest.dto.ClaseDTO;
import com.colegio.machado.infraestructure.rest.dto.ClaseRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaseDTOMapper {

    @Mapping(source = "nombre", target = "clase")
    @Mapping(target = "alumnos", ignore = true)
    ClaseDTO toDTO(Clase clase);

    @Mapping(source = "clase", target = "nombre")
    Clase toModel(ClaseDTO claseDTO);

    List<ClaseDTO> mapDTO(List<Clase> clases);

    List<Clase> mapModel(List<ClaseDTO> claseDTO);


    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "profesor", target = "profesor")
    Clase toModel(ClaseRequestDTO dto);
}
