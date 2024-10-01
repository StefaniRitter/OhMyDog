package com.pi.OhMyDog.service;

import com.pi.OhMyDog.data.PacienteEntity;
import com.pi.OhMyDog.data.PacienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

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
    
    public void deletar(int id) {
        pacienteRepository.deleteById(id);
    }
    
    
    
    
}
