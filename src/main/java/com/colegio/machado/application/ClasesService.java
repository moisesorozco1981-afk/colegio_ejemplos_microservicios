package com.colegio.machado.application;

import com.colegio.machado.application.exceptions.NotFoundException;
import com.colegio.machado.application.usecase.*;
import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.domain.ports.out.ClaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasesService implements GetAllClasesUseCase, GetClaseByIdUseCase, CreateClaseUseCase, UpdateClaseUseCase, DeleteClaseUseCase {
    private final ClaseRepository claseRepository;

    private static final Logger log = LoggerFactory.getLogger(ClasesService.class);

    //Constructor
    public ClasesService(ClaseRepository claseRepository) {
        this.claseRepository = claseRepository;
    }

    //Buscar todas las clases
    @Override
    public List<Clase> execute() {
        log.info("Obteniendo datos de todas las clases");
        return claseRepository.findAll();
    }

    //Buscar clase por ID
    @Override
    public Clase execute(Long idClase) {
        log.info("Buscando datos de la clase con id {}", idClase);
        Clase clase = getClaseOrThrow(idClase);
        log.info("Clase con id {} encontrada", idClase);
        return clase;
    }

    //Agregar clase
    @Override
    public Clase execute(Clase clase) {
        log.info("Creando clase con nombre {}", clase.getNombre());
        Clase saved = claseRepository.save(clase);
        log.info("Clase creada correctamente con id {}", saved.getId());
        return saved;
    }

    //Actualizar Clase
    @Override
    public Clase execute(Long idClase, Clase clase) {
        log.info("Actualizando clase con id {}", idClase);
        Clase existente = getClaseOrThrow(idClase);
        existente.setNombre(clase.getNombre());
        existente.setProfesor(clase.getProfesor());
        Clase saved = claseRepository.save(existente);

        log.info("Clase con id {} actualizada", saved.getId());
        return saved;
    }

    @Override
    public void executeDelete(Long idClase) {
        log.info("Eliminando clase con id {}", idClase);

        getClaseOrThrow(idClase);

        claseRepository.delete(idClase);
        log.info("Clase con id {} eliminada correctamente", idClase);
    }

    private Clase getClaseOrThrow(Long idClase) {
        Clase clase = claseRepository.findById(idClase);
        if (clase == null) {
            log.error("Clase con id {} no encontrada", idClase);
            throw new NotFoundException("Clase no encontrada");
        }
        return clase;
    }
}
