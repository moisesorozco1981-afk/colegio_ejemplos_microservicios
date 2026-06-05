package com.colegio.machado.application;

import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.domain.model.Clase;
import com.colegio.machado.infraestructure.database.AlumnosRepository;
import com.colegio.machado.infraestructure.database.ClaseRepository;
import com.colegio.machado.infraestructure.database.entities.AlumnoEntity;
import com.colegio.machado.infraestructure.database.entities.ClaseEntity;
import com.colegio.machado.infraestructure.database.mappers.AlumnoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnosService {


    @Autowired
    ClaseRepository claseRepository;
    @Autowired
    AlumnosRepository alumnosRepository;
    @Autowired
    AlumnoEntityMapper alumnoMapper;

    public List<Alumno> getAllAlumnos() {
        List<AlumnoEntity> alumnosEntity = alumnosRepository.findAll();
        List<Alumno> alumnos = alumnoMapper.mapModel(alumnosEntity);

        return alumnos;
    }

    public Alumno getAlumnoById(Long id) {

        AlumnoEntity alumnoEntity = alumnosRepository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Alumno alumno = alumnoMapper.toModel(alumnoEntity);

        if (alumnoEntity.getClase() != null) {
            alumno.setClase(new Clase(alumnoEntity.getClase().getId(), alumnoEntity.getClase().getClase(), alumnoEntity.getClase().getProfesor(), null));
        }

        return alumno;
    }

    public Alumno guardarAlumno(Alumno alumno, Long idClase) {


        // 🔥 1. Buscar la clase
        ClaseEntity clase = claseRepository.findById(idClase).orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        // 🔥 2. Convertir modelo → entity
        AlumnoEntity entity = alumnoMapper.toEntity(alumno);

        // 🔥 3. Asignar la clase (CLAVE)
        entity.setClase(clase);

        // 🔥 4. Guardar
        AlumnoEntity guardado = alumnosRepository.save(entity);

        // 🔥 5. Convertir entity → modelo
        return alumnoMapper.toModel(guardado);
    }

    public Alumno actualizarAlumno(Long id, Alumno alumno, Long idClase) {

        ClaseEntity clase = claseRepository.findById(idClase).orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        AlumnoEntity entity = alumnosRepository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrada"));
        entity.setClase(clase);
        alumnoMapper.updateEntity(alumno, entity);

        AlumnoEntity guardado = alumnosRepository.save(entity);

        return alumnoMapper.toModel(guardado);

    }

    public void eliminarAlumno(Long id) {

        AlumnoEntity alumno = alumnosRepository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        alumnosRepository.delete(alumno);
    }
}
