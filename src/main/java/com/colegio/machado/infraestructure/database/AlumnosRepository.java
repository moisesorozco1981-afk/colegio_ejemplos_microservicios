package com.colegio.machado.infraestructure.database;

import com.colegio.machado.infraestructure.database.entities.AlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnosRepository extends JpaRepository<AlumnoEntity, Long> {
}
