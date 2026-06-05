package com.colegio.machado.application.usecase;

import com.colegio.machado.domain.model.Clase;

import java.util.List;


public interface GetAllClasesUseCase
{
    List<Clase> execute();

}
