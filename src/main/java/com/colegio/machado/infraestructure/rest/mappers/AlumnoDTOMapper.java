package com.colegio.machado.infraestructure.rest.mappers;

import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.rest.dto.AlumnoDTO;
import com.colegio.machado.infraestructure.rest.dto.AlumnoRequestDTO;
import com.colegio.machado.infraestructure.rest.dto.SimpleClaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnoDTOMapper {
        // @Mapping(source = "name", target = "nombreDelChaval")
        AlumnoDTO toDTO(Alumno alumno);

        Alumno toModel(AlumnoDTO alumnoDTO);

        List<AlumnoDTO> mapDTO(List<Alumno> alumnos);

        List<Alumno> mapModel(List<AlumnoDTO> alumnosDTO);

        default SimpleClaseDTO map(Clase clase) {
                if (clase == null)
                        return null;

                return new SimpleClaseDTO(
                                clase.getId(),
                                clase.getClase());
        }

        default Clase map(SimpleClaseDTO claseDTO) {
                if (claseDTO == null)
                        return null;

                return new Clase(
                                claseDTO.id(),
                                claseDTO.clase(),
                                null,
                                null);
        }

        //NUEVO PARA POST/PUT
        @Mapping(target = "clase", ignore = true)
        @Mapping(target = "id", ignore = true)
        Alumno toModel(AlumnoRequestDTO dto);

}
