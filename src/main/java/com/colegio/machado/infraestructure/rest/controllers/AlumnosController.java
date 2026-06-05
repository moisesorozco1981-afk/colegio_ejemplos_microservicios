package com.colegio.machado.infraestructure.rest.controllers;

import com.colegio.machado.application.AlumnosService;
import com.colegio.machado.application.usecase.GetAllAlumnosUseCase;
import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.infraestructure.rest.dto.AlumnoDTO;
import com.colegio.machado.infraestructure.rest.dto.AlumnoRequestDTO;
import com.colegio.machado.infraestructure.rest.mappers.AlumnoDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnosController {

    @Autowired
    private AlumnosService alumnosService;
    @Autowired
    private AlumnoDTOMapper alumnoDTOMapper;

    private final GetAllAlumnosUseCase getAllAlumnosUseCase;

    /**
     * Explicación del método
     * @return explicar qué devuelve
     */
    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> getAlumnos(){
        List<Alumno> alumnos= getAllAlumnosUseCase.execute();
        List<AlumnoDTO> alumnoDTOList = alumnoDTOMapper.mapDTO(alumnos);
        return ResponseEntity.ok(alumnoDTOList);
    }

    @GetMapping("/{id}")
    //@GetMapping("/alumno")
    public ResponseEntity<AlumnoDTO> getAlumnoByID(@PathVariable Long id){
        return ResponseEntity.ok(alumnoDTOMapper.toDTO(alumnosService.getAlumnoById(id)));
    }


    @PostMapping
    public ResponseEntity<AlumnoDTO> guardarAlumno(@RequestBody AlumnoRequestDTO request){
        // DTO → Modelo
        Alumno alumno = alumnoDTOMapper.toModel(request);
        // Guardar con idClase
        Alumno guardado = alumnosService.guardarAlumno(alumno, request.idClase());
        // Modelo → DTO
        AlumnoDTO response = alumnoDTOMapper.toDTO(guardado);
        return ResponseEntity
                .created(URI.create("/alumnos/" + response.id()))
                .body(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<AlumnoDTO> actualizarAlumno(@PathVariable Long id, @RequestBody AlumnoRequestDTO request) {

        Alumno alumno = alumnoDTOMapper.toModel(request);

        Alumno actualizado = alumnosService.actualizarAlumno(id, alumno, request.idClase());

        AlumnoDTO response = alumnoDTOMapper.toDTO(actualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("/{id}")
    public void eliminarAlumno(@PathVariable Long id){
        alumnosService.eliminarAlumno(id);
    }

}
