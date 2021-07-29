package com.accenture.bank.servico;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
}
