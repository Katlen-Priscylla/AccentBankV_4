package com.accenture.bank.servico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Extrato;
import com.accenture.bank.entity.Transacao;
import com.accenture.bank.exceptions.ContaNaoEncontradaException;
import com.accenture.bank.exceptions.FormatoDeContaInvalidoException;
import com.accenture.bank.exceptions.TransacaoInvalidaException;
import com.accenture.bank.exceptions.ValorInvalidoException;
import com.accenture.bank.repository.ContaCorrenteRepository;
import com.accenture.bank.repository.ExtratoRepository;

class ContaCorrenteServiceTest {

	@InjectMocks
	ContaCorrenteService service;

	@Mock
	ContaCorrenteRepository repository;
	
	@Mock
	ExtratoRepository extratoRepository;

	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void deveLancarErroAoNaoEncontrarAConta() {

		Long idInvalido = 999L;
		Mockito.when(repository.findById(idInvalido)).thenReturn(Optional.empty());

		Throwable exception = catchThrowable(() -> service.getByIdContaCorrente(idInvalido));

		assertThat(exception).isInstanceOf(ContaNaoEncontradaException.class).hasMessage("Conta nao encontrada");

	}

	@Test
	public void deveSalvarUmaContaComSucesso() {
	

		ContaCorrente contaValida = new ContaCorrente(null, "1212", 0.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		ContaCorrente contaEsperada = new ContaCorrente(1L, "1212", 0.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaEsperada.getAgencia().setIdAgencia(1L);
		contaEsperada.getCliente().setIdCliente(1L);
		Mockito.when(repository.save(contaValida)).thenReturn(contaEsperada);
		
		ContaCorrente contaRetornada = service.saveConta(contaValida);
		

		assertThat(contaRetornada.getIdContaCorrente()).isNotNull();

	}

	@Test
	public void deveLancarErroDeFormatoParaContaInvalida() {


		ContaCorrente contaInvalida = new ContaCorrente(null, "1212", 0.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());

		Mockito.when(repository.save(contaInvalida)).thenReturn(contaInvalida);
		
		Throwable exception = catchThrowable(() -> service.saveConta(contaInvalida));
		

		assertThat(exception).isInstanceOf(FormatoDeContaInvalidoException.class);

	}
	
	@Test
	public void deveLancarErroSacandoValorMenorQueDois() {
		ContaCorrente contaValida = new ContaCorrente(null, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		
		
		
		Mockito.when(repository.findById(1L))
		.thenReturn(Optional.of(contaValida));
		
		Throwable exception = catchThrowable(() -> service.saque(1L, 0, Transacao.SAQUE));
		
		assertThat(exception).isInstanceOf(ValorInvalidoException.class);
		assertThat(exception).hasMessage("Valor inválido.");
	}
	
	@Test
	public void deveSacarValorQuandoSaldoForSuficiente() {
		final double SALDO_INICAL = 100.0;
		final double VALOR_SAQUE = 50.0;
		final double SALDO_FINAL = 50.0;
		
		ContaCorrente contaValida = new ContaCorrente(1L, "1212", SALDO_INICAL, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		Extrato extrato = new Extrato(null, LocalDateTime.now(), VALOR_SAQUE, Transacao.SAQUE, contaValida);
				
		Mockito.when(repository.findById(1L))
		.thenReturn(Optional.of(contaValida));
		
		Mockito.when(extratoRepository.save(extrato)).thenReturn(extrato);
		
		
		ContaCorrente contaSacada = service.saque(contaValida.getIdContaCorrente(), VALOR_SAQUE, Transacao.SAQUE);
		
		assertThat(contaSacada.getSaldo()).isEqualTo(SALDO_FINAL);
		
		
	}
	
	@Test
	public void deveLancarErroSacandoValorMaiorQueSaldo() {
		ContaCorrente contaValida = new ContaCorrente(null, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		
		
		
		Mockito.when(repository.findById(1L))
		.thenReturn(Optional.of(contaValida));
		
		Throwable exception = catchThrowable(() -> service.saque(1L, 51.0, Transacao.SAQUE));
		
		assertThat(exception).isInstanceOf(ValorInvalidoException.class);
		assertThat(exception).hasMessage("Saldo insuficiente.");
	}

	@Test
	public void deveLancarErroDepositandoValorNegativo(){
		ContaCorrente contaValida = new ContaCorrente(null, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		
		Mockito.when(repository.findById(1L))
		.thenReturn(Optional.of(contaValida));
		
		Throwable exception = catchThrowable(() -> service.deposita(1L, 0.0, Transacao.DEPOSITO));
		
		assertThat(exception).isInstanceOf(ValorInvalidoException.class);
		assertThat(exception).hasMessage("Valor inválido para depósito.");
	}
	
	@Test
	public void deveLancarErroAoTransferirParaMesmaConta() {
		ContaCorrente contaOrigem = new ContaCorrente(1L, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaOrigem.getAgencia().setIdAgencia(1L);
		contaOrigem.getCliente().setIdCliente(1L);
		
		ContaCorrente contaDestino = new ContaCorrente(1L, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaDestino.getAgencia().setIdAgencia(1L);
		contaDestino.getCliente().setIdCliente(1L);
		
		Throwable exception = catchThrowable(() -> service.transfere(contaOrigem.getIdContaCorrente(), contaDestino.getIdContaCorrente(), 20.0));
		
		assertThat(exception).isInstanceOf(TransacaoInvalidaException.class);
		assertThat(exception).hasMessage("Conta inválida para transferência.");
	}
}
