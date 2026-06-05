package com.colegio.machado.application.usecase;

import com.colegio.machado.domain.model.Alumno;
import com.colegio.machado.domain.model.AlumnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllAlumnosUseCase
{

    private final AlumnoRepository alumnoRepository;

    public List<Alumno> execute() {
        //Podría haber mogollón de lógica de negocio aquí
        return alumnoRepository.findAll();
    }
}
