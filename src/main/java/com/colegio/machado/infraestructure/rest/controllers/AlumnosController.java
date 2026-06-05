package com.colegio.machado.infraestructure.rest.controllers;

import com.colegio.machado.application.usecase.*;
import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.infraestructure.rest.dto.AlumnoDTO;
import com.colegio.machado.infraestructure.rest.dto.AlumnoRequestDTO;
import com.colegio.machado.infraestructure.rest.mappers.AlumnoDTOMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnosController {

    private final AlumnoDTOMapper alumnoDTOMapper;
    private final GetAllAlumnosUseCase getAllAlumnosUseCase;
    private final GetAlumnoByIdUseCase getAlumnoByIdUseCase;
    private final CreateAlumnoUseCase createAlumnoUseCase;
    private final UpdateAlumnoUseCase updateAlumnoUseCase;
    private final DeleteAlumnoUseCase deleteAlumnoUseCase;

    /**
     * Explicación del método
     * @return explicar qué devuelve
     */
    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> getAlumnos(){
        List<Alumno> alumnos= getAllAlumnosUseCase.execute();
        return ResponseEntity.ok(alumnoDTOMapper.mapDTO(alumnos));
    }

    @GetMapping("/{id}")
    //@GetMapping("/alumno")
    public ResponseEntity<AlumnoDTO> getAlumnoByID(@PathVariable Long id){
        return ResponseEntity.ok(alumnoDTOMapper.toDTO(getAlumnoByIdUseCase.execute(id)));
    }


    @PostMapping
    public ResponseEntity<AlumnoDTO> guardarAlumno(@RequestBody @Valid AlumnoRequestDTO request){
        // DTO → Modelo
        Alumno alumno = alumnoDTOMapper.toModel(request);
        AlumnoDTO response = alumnoDTOMapper.toDTO(createAlumnoUseCase.execute(alumno, request.clase()));
        return ResponseEntity
                .created(URI.create("/alumnos/" + response.id()))
                .body(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<AlumnoDTO> actualizarAlumno(@PathVariable Long id, @RequestBody AlumnoRequestDTO request) {

        Alumno alumno = alumnoDTOMapper.toModel(request);

        Alumno actualizado = updateAlumnoUseCase.execute(id, alumno, request.clase());

        AlumnoDTO response = alumnoDTOMapper.toDTO(actualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void>  eliminarAlumno(@PathVariable Long id){
        deleteAlumnoUseCase.executeDelete(id);
        return ResponseEntity.noContent().build();
    }

}
