package com.pi.OhMyDog.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consultas")
@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class ConsultaEntity  {
    
     @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    private PacienteEntity paciente; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String data;
    private String hora;
    private String descricao;
    private String pago = "Não";
}