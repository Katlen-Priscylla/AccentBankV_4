package com.accenture.bank.servico;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accenture.bank.entity.Extrato;
import com.accenture.bank.exceptions.ContaNaoEncontradaException;
import com.accenture.bank.repository.ContaCorrenteRepository;

@ExtendWith(SpringExtension.class)

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
		// cenario
		long idInvalido = 366L;

		Mockito.when(repository.findById(idInvalido)).thenReturn(Optional.empty());

		// validacao
	Throwable excessao = Assertions.catchThrowable(() -> service.getByContaId(idInvalido));
	
	Assertions.assertThat(excessao)
	.isInstanceOf(ContaNaoEncontradaException.class)
	.hasMessage("Conta nao encontrada");

	}
}
