package com.accenture.bank.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAgencia;
	private String nome;
	private String endereco;
//	
//	@OneToOne
//	private Endereco endereco;
	
	@OneToMany(mappedBy = "agencia")
	@JsonIgnore
	private List<ContaCorrente> contas = new ArrayList<>();



	
}
