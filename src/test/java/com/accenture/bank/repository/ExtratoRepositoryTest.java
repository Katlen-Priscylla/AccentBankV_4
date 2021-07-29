package com.accenture.bank.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Endereco;
import com.accenture.bank.entity.Extrato;
import com.accenture.bank.entity.Transacao;
import org.junit.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ExtratoRepositoryTest {

	@Autowired
	private ExtratoRepository repository;

	@Autowired
	private TestEntityManager em;

	@Test
	public void deveRetornarUmExtratoPeloId() {

		// cenario

		long idRequerido = 1L;
		// execucao
		// ir no banco e buscar o extrato de Id x

		Optional<Extrato> extratoRetornado = repository.findById(idRequerido);

		// validacao
		// validar se o extrato retornado é o extrato esperado

		Assertions.assertEquals(extratoRetornado.get().getIdExtrato(), idRequerido);

	}
	
	

	
}
