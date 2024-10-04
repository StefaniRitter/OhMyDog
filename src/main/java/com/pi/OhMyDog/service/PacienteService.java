package com.pi.OhMyDog.service;

import com.pi.OhMyDog.data.ConsultaRepository;
import com.pi.OhMyDog.data.PacienteEntity;
import com.pi.OhMyDog.data.PacienteRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
     @Autowired
    private ConsultaRepository consultaRepository;

    public List<PacienteEntity> listarTodos() {
        return pacienteRepository.findAll();
    }
    
    public PacienteEntity buscarPorId(int id) {
        Optional<PacienteEntity> paciente = pacienteRepository.findById(id);
        return paciente.orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }
    
    public List<PacienteEntity> buscarPorNome(String nome) {
        return pacienteRepository.findByNomeContaining(nome);
}
    
    public PacienteEntity salvar(PacienteEntity paciente) {
        return pacienteRepository.save(paciente);
    }
    
    public PacienteEntity atualizar(int id, PacienteEntity pacAtualizado) {
        PacienteEntity pac = pacienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        pac.setNome(pacAtualizado.getNome());
        pac.setEspecie(pacAtualizado.getEspecie());
        pac.setRaca(pacAtualizado.getRaca());
        pac.setSexo(pacAtualizado.getSexo());
        pac.setIdade(pacAtualizado.getIdade());
        pac.setPeso(pacAtualizado.getPeso());
        pac.setObservacoes(pacAtualizado.getObservacoes());
        return pacienteRepository.save(pac);
    }
    
     @Transactional
    public void deletarPaciente(Integer id) {
        // Apagar as consultas associadas ao paciente
        consultaRepository.deleteAllByPaciente_Id(id);
        
        // Agora, apagar o paciente
        pacienteRepository.deleteById(id);
    }

    
    
    
    
}
