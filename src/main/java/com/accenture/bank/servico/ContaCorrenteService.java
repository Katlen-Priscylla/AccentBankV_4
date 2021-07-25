package com.accenture.bank.servico;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Extrato;
import com.accenture.bank.entity.Transacao;
import com.accenture.bank.repository.ContaCorrenteRepository;
import com.accenture.bank.repository.ExtratoRepository;

@Service
public class ContaCorrenteService {

//	@Autowired
//	AgenciaRepository agenciaRepository;
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
//	@Autowired
//	ClienteRepository clienteRepository;
	@Autowired
	ExtratoRepository extratoRepository;

	public ContaCorrente saveConta(ContaCorrente contaCorrente) {
//		agenciaRepository.save(contaCorrente.getAgencia());
//		clienteRepository.save(contaCorrente.getCliente());

		return contaCorrenteRepository.save(contaCorrente);

	}

	public List<ContaCorrente> getAllContaCorrente() {

		return contaCorrenteRepository.findAll();

	}

	public ContaCorrente getByIdContaCorrente(Long id) {
		return contaCorrenteRepository.findById(id).get();
	}

	public ContaCorrente atualizaContaCorrente(long id, ContaCorrente newContaCorrente) {
		newContaCorrente.setIdContaCorrente(id);
		ContaCorrente oldContaCorrente = contaCorrenteRepository.findById(id).get();
		if (oldContaCorrente != null) {
			
			oldContaCorrente.setValor(newContaCorrente.getValor());
			newContaCorrente = oldContaCorrente;
			return contaCorrenteRepository.save(newContaCorrente);
		} else
			return null;
	}

	public void deletaContaCorrente(Long id) {
		contaCorrenteRepository.deleteById(id);
	}
	
	public ContaCorrente saque(Long id , double valor) {

		double valortransacao = valor;
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).get();
		valor = contaCorrente.getValor() - valor ;
		contaCorrente.setValor(valor);
		contaCorrenteRepository.save(contaCorrente);
		Date date = new Date();
	
		Extrato extrato= new Extrato(null,date,valortransacao,Transacao.SAQUE,contaCorrente);
		extratoRepository.save(extrato);
		return contaCorrente;
	}
	public ContaCorrente deposita(Long id , double valor) {
		double valorTransacao = valor;
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).get();
		valor = contaCorrente.getValor() + valor ;
		contaCorrente.setValor(valor);
		contaCorrenteRepository.save(contaCorrente);
		
		Date date = new Date();
		
		Extrato extrato= new Extrato(null,date,valorTransacao,Transacao.DEPOSITO,contaCorrente);
		extratoRepository.save(extrato);
		
		return contaCorrente;
	}
	public Map<String,ContaCorrente> transfere(Long idOrigem, Long idDestino, double valor) {
	
		double valorTransacao = valor;
		ContaCorrente origem =saque(idOrigem,valor);
       Date date = new Date();
		Extrato extrato= new Extrato(null,date,valorTransacao,Transacao.TRANSFERENCIA,origem);
		extratoRepository.save(extrato);
		ContaCorrente destino = deposita(idDestino,valor);
		Extrato extrato1= new Extrato(null,date,valorTransacao,Transacao.DEPOSITO,destino);
		extratoRepository.save(extrato1);
		Map<String,ContaCorrente> contas = new HashMap<>();
		contas.put("origem",origem);
		contas.put("destino", destino);
		
		return contas;
	}
}
