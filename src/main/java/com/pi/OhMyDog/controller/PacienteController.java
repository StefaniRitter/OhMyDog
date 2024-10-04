package com.pi.OhMyDog.controller;

import com.pi.OhMyDog.data.PacienteEntity;
import com.pi.OhMyDog.service.PacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    
        @Autowired
    private PacienteService pacService;
    
    @GetMapping("/cadastro")
    public String exibirFormularioPac(Model model) {
        model.addAttribute("paciente", new PacienteEntity()); 
        return "cadastro"; 
    }
    
    @PostMapping("/cadastro")
    public String adicionarPaciente(@ModelAttribute PacienteEntity pac) {
       pacService.salvar(pac); 
        return "redirect:/pacientes/lista"; 
    }
    
    @GetMapping("/lista")
    public String listarTodos(Model model) {
        List<PacienteEntity> pacientes = pacService.listarTodos(); 
        model.addAttribute("pacientes", pacientes);
        return "pacientes"; 
    }
    
     @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable int id, Model model) {
        PacienteEntity paciente = pacService.buscarPorId(id); 
        if (paciente != null) {
            model.addAttribute("paciente", paciente); 
        } else {
            model.addAttribute("erro", "Paciente não encontrado!");
            return "redirect:/pacientes/lista"; 
        }
        return "edicaoPaciente"; 
    }
    
    @PostMapping("/editar/{id}")
    public String atualizarPac(@PathVariable int id, @ModelAttribute PacienteEntity pacAtualizado, Model model) {
        PacienteEntity pacExistente = pacService.buscarPorId(id); 
        if (pacExistente != null) {
            pacExistente.setNome(pacAtualizado.getNome());
            pacExistente.setEspecie(pacAtualizado.getEspecie());
            pacExistente.setRaca(pacAtualizado.getRaca());
            pacExistente.setSexo(pacAtualizado.getSexo());
            pacExistente.setIdade(pacAtualizado.getIdade());
            pacExistente.setPeso(pacAtualizado.getPeso());
            pacExistente.setObservacoes(pacAtualizado.getObservacoes());
            pacService.atualizar(id, pacExistente); 
        }
        return "redirect:/pacientes/lista"; 
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletarPaciente(@PathVariable int id) {
        pacService.deletarPaciente(id); 
    }
    
    
}
