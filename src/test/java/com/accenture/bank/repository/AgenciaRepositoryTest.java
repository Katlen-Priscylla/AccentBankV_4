package com.accenture.bank.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Endereco;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AgenciaRepositoryTest {
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	

	@Test
	public void testCreateAgencia() {
		 List <ContaCorrente> contas = new ArrayList<>();
		 Endereco endereco = new Endereco();
		
		Agencia agencia = agenciaRepository.save(new Agencia(1L,"Teste","77777.9999",endereco,contas));
		
		assertNotNull(agencia);
		assertTrue(agencia.getIdAgencia()>0);
		
	}
	
	@Test
	public void deveRetornarUmAgenciaPorId() {
		
		Agencia agencia = agenciaRepository.findById(1L).get();
		
		Assertions.assertThat(agencia.getIdAgencia().compareTo(1L));
		
	}

}
