package com.pi.OhMyDog.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "filmes")
@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     private String nome;
    private String especie;
    private String raca;
    private String sexo;
    private String idade;
    private String peso;
    private String observacoes;
    
}

