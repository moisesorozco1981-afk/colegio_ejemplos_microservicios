package com.colegio.machado.infraestructure.rest.controllers;

import com.colegio.machado.application.usecase.*;
import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.rest.dto.ClaseDTO;
import com.colegio.machado.infraestructure.rest.dto.ClaseRequestDTO;
import com.colegio.machado.infraestructure.rest.mappers.ClaseDTOMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clases")
@RequiredArgsConstructor
public class ClasesController {

    private final ClaseDTOMapper claseDTOMapper;
    private final GetAllClasesUseCase getAllClasesUseCase;
    private final GetClaseByIdUseCase getClaseByIdUseCase;
    private final CreateClaseUseCase createClaseUseCase;
    private final UpdateClaseUseCase updateClaseUseCase;
    private final DeleteClaseUseCase deleteClaseUseCase;

    /**
     * Explicación del método
     *
     * @return explicar qué devuelve
     */
    //Todas las clases
    @GetMapping
    public ResponseEntity<List<ClaseDTO>> getClases() {
        List<Clase> clases = getAllClasesUseCase.execute();
        return ResponseEntity.ok(claseDTOMapper.mapDTO(clases));
    }

    //Clase por id
    @GetMapping("/{id}")
    public ResponseEntity<ClaseDTO> getClaseByID(@PathVariable Long id) {
        return ResponseEntity.ok(claseDTOMapper.toDTO(getClaseByIdUseCase.execute(id)));
    }

    //Guardar la clase
    @PostMapping
    public ResponseEntity<ClaseDTO> guardarClase(@RequestBody @Valid ClaseRequestDTO request) {
        // DTO → Modelo
        Clase clase = claseDTOMapper.toModel(request);
        ClaseDTO response = claseDTOMapper.toDTO(createClaseUseCase.execute(clase));
        return ResponseEntity.created(URI.create("/clases/" + response.id())).body(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClaseDTO> actualizarClase(@PathVariable Long id, @RequestBody @Valid ClaseRequestDTO request) {

        Clase clase = claseDTOMapper.toModel(request);

        Clase actualizado = updateClaseUseCase.execute(id, clase);

        ClaseDTO response = claseDTOMapper.toDTO(actualizado);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClase(@PathVariable Long id) {
        deleteClaseUseCase.executeDelete(id);
        return ResponseEntity.noContent().build();
    }
}
