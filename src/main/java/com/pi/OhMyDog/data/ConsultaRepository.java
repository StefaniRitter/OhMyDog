package com.pi.OhMyDog.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer> {
    @Query("SELECT c FROM ConsultaEntity c JOIN PacienteEntity p ON c.idPet = p.id WHERE p.nome LIKE %:nomePet%")
            List<ConsultaEntity> findByNomePet(@Param("nomePet") String nomePet);

}
