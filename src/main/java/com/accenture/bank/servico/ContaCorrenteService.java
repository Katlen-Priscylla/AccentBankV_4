package com.accenture.bank.servico;

import java.time.LocalDateTime;
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

	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	ExtratoRepository extratoRepository;

	public ContaCorrente saveConta(ContaCorrente contaCorrente) {

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

			oldContaCorrente.setSaldo(newContaCorrente.getSaldo());
			newContaCorrente = oldContaCorrente;
			return contaCorrenteRepository.save(newContaCorrente);
		} else
			return null;
	}

	public void deletaContaCorrente(Long id) {
		contaCorrenteRepository.deleteById(id);
	}

	public ContaCorrente saque(Long id, double valor, Transacao transacao) {
		
//		if(valor <= 0) {
//			throw
//		}

		double valortransacao = valor;
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).get();
		valor = contaCorrente.getSaldo() - valor;
		contaCorrente.setSaldo(valor);
		contaCorrenteRepository.save(contaCorrente);
		Extrato extrato = new Extrato(null, LocalDateTime.now(), valortransacao, transacao, contaCorrente);
		extratoRepository.save(extrato);
		return contaCorrente;
	}

	public ContaCorrente deposita(Long id, double valor, Transacao transacao) {
		double valorTransacao = valor;
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).get();
		valor = contaCorrente.getSaldo() + valor;
		contaCorrente.setSaldo(valor);
		contaCorrenteRepository.save(contaCorrente);

		Extrato extrato = new Extrato(null, LocalDateTime.now(), valorTransacao, transacao, contaCorrente);
		extratoRepository.save(extrato);

		return contaCorrente;
	}

	public Map<String, ContaCorrente> transfere(Long idOrigem, Long idDestino, double valor) {

		ContaCorrente origem = saque(idOrigem, valor, Transacao.TRANSFERENCIA);

		ContaCorrente destino = deposita(idDestino, valor, Transacao.TRANSFERENCIA);

		Map<String, ContaCorrente> contas = new HashMap<>();
		contas.put("origem", origem);
		contas.put("destino", destino);

		return contas;
	}
}
