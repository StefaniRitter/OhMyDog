package com.pi.OhMyDog.service;

import com.pi.OhMyDog.data.ConsultaEntity;
import com.pi.OhMyDog.data.ConsultaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consRepository;

    public List<ConsultaEntity> listarTodas() {
        return consRepository.findAll();
    }

    public List<ConsultaEntity> listarConsultasPorPet(String nomePet) {
        return consRepository.findByNomePet(nomePet);
    }

    public ConsultaEntity salvarConsulta(ConsultaEntity consulta) {
        return consRepository.save(consulta);
    }

    public ConsultaEntity atualizarCons(int id, ConsultaEntity consultaRequest) {
        Optional<ConsultaEntity> consultaExistente = consRepository.findById(id);
        if (consultaExistente.isPresent()) {
            ConsultaEntity cons = consultaExistente.get();
            cons.setData(consultaRequest.getData());
            cons.setHora(consultaRequest.getHora());
            cons.setDescricao(consultaRequest.getDescricao());
            cons.setPago(consultaRequest.isPago());
            cons.setPaciente(consultaRequest.getPaciente()); // Atualiza o paciente
            return consRepository.save(cons);
        }
        return null;
    }

    public boolean deletarConsulta(int id) {
        Optional<ConsultaEntity> cons = consRepository.findById(id);
        if (cons.isPresent()) {
            consRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ConsultaEntity buscarPorId(int id) {
        Optional<ConsultaEntity> consulta = consRepository.findById(id);
        return consulta.orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }
}
