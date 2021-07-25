package com.accenture.bank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaCorrente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContaCorrente;
	private String ContaCorrenteNumero;
	private double valor;
	
	@ManyToOne
	
	private Agencia agencia;
	
	
//	private Long idAgencia1= agencia.getIdAgencia();
	
	@OneToOne
private Cliente cliente;
	



}
