package com.accenture.bank.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
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
import com.accenture.bank.exceptions.ContaNaoEncontradaException;
import com.accenture.bank.repository.ContaCorrenteRepository;



public class ExtratoServiceTest {

	@InjectMocks
	ExtratoService service;

	@Mock
	ContaCorrenteRepository repository;
	
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void deveLancarErroAoNaoEncontrarExtrato() {
		
		long idInvalido = 366L;

		Mockito.when(repository.findById(idInvalido)).thenReturn(Optional.empty());

		
	Throwable exception = Assertions.catchThrowable(() -> service.getByContaId(idInvalido));
	
	Assertions.assertThat(exception)
	.isInstanceOf(ContaNaoEncontradaException.class)
	.hasMessage("Conta nao encontrada");

	}
	
	@Test
	public void deveRetornarExtratoDaContaPeloId() {
		long idValido = 1L;
		
		List<Extrato> transacoes= new ArrayList<>();
		ContaCorrente conta = new ContaCorrente(1L, "2233", 100.0, new Agencia(), new Cliente(), transacoes);
		transacoes.add(new Extrato());
		transacoes.add(new Extrato());
		transacoes.add(new Extrato());
		
		Mockito.when(repository.findById(idValido)).thenReturn(Optional.of(conta));
		
		Optional<ContaCorrente> contaRetornada = repository.findById(idValido);
		
		Assertions
		.assertThat(contaRetornada.get().getIdContaCorrente()).isEqualTo(conta.getIdContaCorrente());
		Assertions.assertThat(contaRetornada.get().getTransacoes()).hasSize(3);
	}
}
