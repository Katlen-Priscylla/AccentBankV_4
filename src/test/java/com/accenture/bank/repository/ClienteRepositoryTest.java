package com.accenture.bank.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.Endereco;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteRepositoryTest {
	

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	public void testCreateCliente() {
		
		Cliente cliente = clienteRepository.save(new Cliente(null,"Josefina","092.832.534-26","494999797979",new Endereco()));
		
		assertNotNull(cliente);
		assertTrue(cliente.getIdCliente()>0);
	}
	 
	@Test
	public void deveRetornarUmClientePorId() {
		
		Cliente cliente = clienteRepository.findById(1L).get();
		
		Assertions.assertThat(cliente.getIdCliente().compareTo(1L));
		
	}
}
