package com.colegio.machado.application;

import com.colegio.machado.application.exceptions.NotFoundException;
import com.colegio.machado.application.usecase.*;
import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.domain.ports.out.AlumnoRepository;
import com.colegio.machado.domain.ports.out.ClaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnosService implements GetAllAlumnosUseCase,
        GetAlumnoByIdUseCase,
        CreateAlumnoUseCase,
        UpdateAlumnoUseCase,
        DeleteAlumnoUseCase {

    private final ClaseRepository claseRepository;

    private final AlumnoRepository alumnoRepository;

    private static final Logger log = LoggerFactory.getLogger(AlumnosService.class);

    public AlumnosService(ClaseRepository claseRepository, AlumnoRepository alumnoRepository) {
        this.claseRepository = claseRepository;
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    public List<Alumno> execute() {
        log.info("Obteniendo datos de todos los alumnos");
        return alumnoRepository.findAll();
    }

    //Busqueda de un alumno
    @Override
    public Alumno execute(Long idAlumno) {
        log.info("Buscando alumno con id {}", idAlumno);
        Alumno alumno = getAlumnoOrThrow(idAlumno);
        log.info("Alumno encontrado: {}", alumno.getId());
        return alumno;
    }

    //Create
    @Override
    public Alumno execute(Alumno alumno, Long idClase) {
        log.info("Creando alumno con nombre {}", alumno.getNombre());
        Clase clase = getClaseOrThrow(idClase);
        alumno.setClase(clase);
        Alumno saved = alumnoRepository.save(alumno);
        log.info("Alumno creado correctamente con id {}", saved.getId());
        return saved;
    }

    //Update
    @Override
    public Alumno execute(Long idAlumno, Alumno alumno, Long idClase) {
        log.info("Actualizando alumno con id {}", idAlumno);
        Alumno existente = getAlumnoOrThrow(idAlumno);
        Clase clase = getClaseOrThrow(idClase);

        existente.setNombre(alumno.getNombre());
        existente.setApellido(alumno.getApellido());
        existente.setEdad(alumno.getEdad());
        existente.setClase(clase);

        Alumno saved = alumnoRepository.save(existente);
        log.info("Alumno con Id {} actualizado correctamente", saved.getId());
        return saved;
    }

    //Delete
    @Override
    public void executeDelete(Long idAlumno) {

        log.info("Eliminando alumno con id {}", idAlumno);

        Alumno alumno = getAlumnoOrThrow(idAlumno);
        alumnoRepository.delete(idAlumno);
        log.info("Alumno con Id {} eliminado correctamente", alumno.getId());
    }

    private Clase getClaseOrThrow(Long idClase) {
        Clase clase = claseRepository.findById(idClase);
        if (clase == null) {
            log.error("Clase con id {} no encontrada", idClase);
            throw new NotFoundException("Clase no encontrada");
        }
        return clase;
    }

    private Alumno getAlumnoOrThrow(Long idAlumno) {
        Alumno alumno = alumnoRepository.findById(idAlumno);
        if (alumno == null) {
            log.error("Alumno con id {} no encontrado", idAlumno);
            throw new NotFoundException("Alumno no encontrado");
        }
        return alumno;
    }
}
