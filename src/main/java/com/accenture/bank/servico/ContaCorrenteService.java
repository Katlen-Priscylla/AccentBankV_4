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
import com.accenture.bank.exceptions.ContaNaoEncontradaException;
import com.accenture.bank.exceptions.FormatoDeContaInvalidoException;
import com.accenture.bank.exceptions.TransacaoInvalidaException;
import com.accenture.bank.exceptions.ValorInvalidoException;
import com.accenture.bank.repository.ContaCorrenteRepository;
import com.accenture.bank.repository.ExtratoRepository;

@Service
public class ContaCorrenteService {

	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;

	@Autowired
	ExtratoRepository extratoRepository;

	public ContaCorrente saveConta(ContaCorrente contaCorrente) {

		if (contaCorrente.getContaCorrenteNumero().isEmpty() || contaCorrente.getContaCorrenteNumero() == null
				|| contaCorrente.getAgencia().getIdAgencia() == null
				|| contaCorrente.getCliente().getIdCliente() == null || Double.isNaN(contaCorrente.getSaldo())
				|| contaCorrente.getSaldo() < 0.0) {
			// retornar uma exception
			throw new FormatoDeContaInvalidoException("Esta conta nao atende aos requesitos de formatacao");
		}

		return contaCorrenteRepository.save(contaCorrente);

	}

	public List<ContaCorrente> getAllContaCorrente() {

		return contaCorrenteRepository.findAll();

	}

	public ContaCorrente getByIdContaCorrente(Long id) {

		if (!contaCorrenteRepository.existsById(id)) {
			throw new ContaNaoEncontradaException();
		}
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
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).get();

		if (valor < 2) {
			throw new ValorInvalidoException("Valor inválido.");
		} else if (valor > contaCorrente.getSaldo()) {
			throw new ValorInvalidoException("Saldo insuficiente.");
		}

		double valortransacao = valor;

		valor = contaCorrente.getSaldo() - valor;
		contaCorrente.setSaldo(valor);
		contaCorrenteRepository.save(contaCorrente);
		Extrato extrato = new Extrato(null, LocalDateTime.now(), valortransacao, transacao, contaCorrente);
		extratoRepository.save(extrato);
		return contaCorrente;
	}

	public ContaCorrente deposita(Long id, double valor, Transacao transacao) {
		ContaCorrente contaCorrente = contaCorrenteRepository.findById(id).get();

		if (valor <= 0) {
			throw new ValorInvalidoException("Valor inválido para depósito.");
		}
		double valorTransacao = valor;
		valor = contaCorrente.getSaldo() + valor;
		contaCorrente.setSaldo(valor);
		contaCorrenteRepository.save(contaCorrente);

		Extrato extrato = new Extrato(null, LocalDateTime.now(), valorTransacao, transacao, contaCorrente);
		extratoRepository.save(extrato);

		return contaCorrente;
	}

	public Map<String, ContaCorrente> transfere(Long idOrigem, Long idDestino, double valor) {
		
		if (idOrigem == idDestino && idDestino == null) {
			throw new TransacaoInvalidaException("Conta inválida para transferência.");
		}

		ContaCorrente origem = saque(idOrigem, valor, Transacao.TRANSFERENCIA);
		ContaCorrente destino = deposita(idDestino, valor, Transacao.TRANSFERENCIA);

		Map<String, ContaCorrente> contas = new HashMap<>();
		contas.put("origem", origem);
		contas.put("destino", destino);

		return contas;
	}
}
