package com.pi.OhMyDog.data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {
    List<PacienteEntity> findByNomeContaining(String nome);

}
