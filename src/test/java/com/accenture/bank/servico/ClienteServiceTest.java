package com.accenture.bank.servico;


	import static org.mockito.Mockito.mock;
	import static org.mockito.Mockito.verify;
	import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

	import javax.validation.ValidationException;

	import org.aspectj.lang.annotation.Before;
	import org.assertj.core.api.Assertions;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.InjectMocks;
	import org.mockito.Mock;
	import org.mockito.Mockito;
	import org.mockito.MockitoAnnotations;

	import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.Endereco;
import com.accenture.bank.exceptions.ClienteNotFoundException;
	import com.accenture.bank.exceptions.ContaNaoEncontradaException;
import com.accenture.bank.exceptions.FormatoIncorretoCliente;
import com.accenture.bank.repository.ClienteRepository;
	import com.accenture.bank.repository.ContaCorrenteRepository;

	 class ClienteServiceTest {


		@InjectMocks
		 private ClienteService clienteService;

		@Mock
		private ClienteRepository clienteRepository;
		
		@BeforeEach
	    public void setUp() {
			

			MockitoAnnotations.initMocks(this);
	    }

		
		@Test
		public void deveLevantarExcecaoQuandoNaoEncontrarIdnoDelete() {
			// cenario
			long idInvalido = 123L;
			
	
		Throwable clienteExcecao = Assertions.catchThrowable(() -> clienteService.deletaCliente(idInvalido));
		
		Assertions.assertThat(clienteExcecao)
		.isInstanceOf(ClienteNotFoundException.class)
		.hasMessage("cliente nao encontrado");
	
		}
		
		@Test
		public void deveSalvarClienteComSucesso() {
			
			Cliente cliente = new Cliente(3L,"Katlen","09283253426","8199999999",new Endereco());
			
			Mockito.when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
			
			Cliente clienteSalvo=clienteRepository.save(new Cliente(null,"Katlen","09283253426","8199999999",new Endereco()));
			
			Assertions.assertThat(clienteSalvo).isEqualTo(cliente);
		}

		@Test
		public void NaodeveSalvarClienteForaDeFormato() {
			
			Cliente cliente = new Cliente(3L,"Katlen","09283253426"," ",new Endereco());
			
			Throwable clienteExcecao = Assertions.catchThrowable(() -> clienteService.saveCliente(cliente));
			
			Assertions.assertThat(clienteExcecao)
			.isInstanceOf(FormatoIncorretoCliente.class)
			.hasMessage("formato incorreto");
		
		}
		
		@Test
		public void deveRetornarTodosOsClientes(){
			
			List<Cliente> clientes = new ArrayList<>();
			
			Cliente cliente = new Cliente(1L, "Katlen", "09283253426", "8199999999", new Endereco());
			Cliente cliente1 = new Cliente(2L, "Joao", "09283253426", "8199999999", new Endereco());
			Cliente cliente2 = new Cliente(3L, "José", "09283253426", "8199999999", new Endereco());

			clientes.add(cliente);
			clientes.add(cliente1);
			clientes.add(cliente2);

			Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
			
			Assertions.assertThat(clientes).isEqualTo(clienteRepository.findAll());
		}
		
		
		@Test
		public void deveLevantarExcecaoQuandoNaoEncontrarIdnoGet() {
			// cenario
			long idInvalido = 123L;
			
	
		Throwable clienteExcecao = Assertions.catchThrowable(() -> clienteService.getById(idInvalido));
		
		Assertions.assertThat(clienteExcecao)
		.isInstanceOf(ClienteNotFoundException.class)
		.hasMessage("cliente nao encontrado");
	
		}
		
//		@Test
//		public void deveLevantarExcecaoQuandoNaoEncontrarIdnoPut() {
//			// cenario
//			long idInvalido = 123L;
//			Cliente cliente = new Cliente(1L,"Joao","092.832.534-26","8888.9999",new Endereco());
//			Cliente cliente2 = new Cliente(3L, "José", "09283253426", "8199999999", new Endereco());
//	
//		Throwable clienteExcecao = Assertions.catchThrowable(() -> clienteRepository.save(cliente));
//		
//		Assertions.assertThat(clienteExcecao)
//		.isInstanceOf(ClienteNotFoundException.class)
//		.hasMessage("cliente nao encontrado");
//		}
	 }
		
	


