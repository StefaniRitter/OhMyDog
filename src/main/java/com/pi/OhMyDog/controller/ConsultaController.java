 package com.pi.OhMyDog.controller;

import com.pi.OhMyDog.data.ConsultaEntity;
import com.pi.OhMyDog.data.PacienteEntity;
import com.pi.OhMyDog.service.ConsultaService;
import com.pi.OhMyDog.service.PacienteService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/consultas")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;
    
    @Autowired
    private PacienteService petService;

    @GetMapping("/novaConsulta")
    public String novaConsulta(Model model) {
        List<PacienteEntity> pacientes = petService.listarTodos();
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("consulta", new ConsultaEntity());
        return "novaConsulta";
    }
    
    @PostMapping("/novaConsulta")
    public String adicionarConsulta(@ModelAttribute ConsultaEntity consulta) {
        consultaService.salvarConsulta(consulta);
        return "redirect:/consultas/agenda";
    }

    @GetMapping("/agenda")
    public String agendaConsultas(Model model) {
        List<ConsultaEntity> consultas = consultaService.listarTodas();

        Map<Integer, String> petsMap = new HashMap<>();
        for (ConsultaEntity consulta : consultas) {
            PacienteEntity pet = consulta.getPaciente(); 
            petsMap.put(consulta.getId(), pet.getNome());
        }

        model.addAttribute("consultas", consultas);
        model.addAttribute("petsMap", petsMap); 
        return "agenda";
    }

    @GetMapping("/editar/{id}")
    public String editarConsulta(@PathVariable Integer id, Model model) {
        ConsultaEntity consulta = consultaService.buscarPorId(id);
        PacienteEntity pet = consulta.getPaciente(); 
        model.addAttribute("consulta", consulta);
        model.addAttribute("pet", pet); 
        return "edicaoConsulta"; 
    }

    @PostMapping("/editar/{id}")
    public String atualizarConsulta(@PathVariable int id, @ModelAttribute ConsultaEntity consAtualizada, Model model) {
        ConsultaEntity consultaExistente = consultaService.buscarPorId(id);
        if (consultaExistente != null) {
            consultaExistente.setData(consAtualizada.getData());
            consultaExistente.setHora(consAtualizada.getHora());
            consultaExistente.setDescricao(consAtualizada.getDescricao());
            consultaExistente.setPago(consAtualizada.isPago());
            consultaExistente.setPaciente(consAtualizada.getPaciente()); 
            consultaService.atualizarCons(id, consultaExistente);
        }
        return "redirect:/consultas/agenda";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletarConsulta(@PathVariable int id) {
        consultaService.deletarConsulta(id);
    }
}
