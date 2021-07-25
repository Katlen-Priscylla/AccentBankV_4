package com.accenture.bank.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Extrato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExtrato;
	private Date dataHoraMovimento;
	private double valorTransacao;
	private Transacao transacao;
	
	@ManyToOne
	@JsonIgnore
	private ContaCorrente contaCorrente;
	



}
