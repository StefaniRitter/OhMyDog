package com.pi.OhMyDog.controller;


import com.pi.OhMyDog.data.ConsultaEntity;
import com.pi.OhMyDog.service.ConsultaService;
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
@RequestMapping("/consultas")
public class ConsultaController {
    
        @Autowired
    private ConsultaService consultaService;
    
    @GetMapping("/novaConsulta")
    public String exibirFormularioConsulta(Model model) {
        model.addAttribute("consulta", new ConsultaEntity()); 
        return "novaConsulta"; 
    }
    
    @PostMapping("/novaConsulta")
    public String adicionarConsulta(@ModelAttribute ConsultaEntity consulta) {
        consultaService.salvarConsulta(consulta); 
        return "redirect:/consultas/agenda"; 
    }
    
    @GetMapping("/agenda")
    public String listarTodos(Model model) {
        List<ConsultaEntity> consultas = consultaService.listarTodas(); 
        model.addAttribute("consultas", consultas);
        return "agenda"; 
    }
    
     @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable int id, Model model) {
        ConsultaEntity consulta = consultaService.buscarPorId(id); 
        if (consulta != null) {
            model.addAttribute("consulta", consulta); 
        } else {
            model.addAttribute("erro", "Consulta não encontrada!");
            return "redirect:/consultas/agenda"; 
        }
        return "edicaoConsulta"; 
    }
    
    @PostMapping("/editar/{id}")
    public String atualizarConsulta(@PathVariable int id, @ModelAttribute ConsultaEntity consAtualizada, Model model) {
        ConsultaEntity consultaExistente = consultaService.buscarPorId(id); 
        if (consultaExistente != null) {
            consultaExistente.setData(consAtualizada.getData());
            consultaExistente.setHora(consAtualizada.getHora());
            consultaExistente.setDescricao(consAtualizada.getDescricao());
            consultaExistente.setPago(consAtualizada.getPago());
            consultaService.atualizarCons(id, consultaExistente); 
        }
        return "redirect:/consultas/" + id; 
    }
    
    //Arrumar aqui pra ir direto pra edição 
    // APAGAR ESSE MÉTODO
    @GetMapping("/{id}")
    public String detalhesConsulta(@PathVariable int id, Model model) {
        ConsultaEntity consulta = consultaService.buscarPorId(id);
        model.addAttribute("consulta", consulta); 
        return "consulta"; 
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    public void deletarConsulta(@PathVariable int id) {
        consultaService.deletarConsulta(id); 
    }
    
    
}
