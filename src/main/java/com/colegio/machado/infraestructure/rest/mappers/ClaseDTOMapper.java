package com.colegio.machado.infraestructure.rest.mappers;


import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.rest.dto.ClaseDTO;
import com.colegio.machado.infraestructure.rest.dto.ClaseRequestDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AlumnoDTOMapper.class})
public interface ClaseDTOMapper {
    //@Mapping(source = "name", target = "nombreDelChaval")
    ClaseDTO toDTO(Clase clase);
    Clase toModel(ClaseDTO claseDTO);

    List<ClaseDTO> mapDTO(List<Clase> clases);
    List<Clase> mapModel(List<ClaseDTO> claseDTO);

    //NUEVO PARA POST/PUT
    Clase toModel(ClaseRequestDTO dto);
}
