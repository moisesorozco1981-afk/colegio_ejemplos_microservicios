package com.colegio.machado.infraestructure.database;

import com.colegio.machado.infraestructure.database.entities.ClaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseJpaRepository extends JpaRepository<ClaseEntity, Long> {

}
