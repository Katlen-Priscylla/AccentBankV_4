package com.accenture.bank.servico;


	import static org.mockito.Mockito.mock;
	import static org.mockito.Mockito.verify;
	import static org.mockito.Mockito.when;

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
		public void deveLevantarExcecaoQuandoNaoEncontrarId() {
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
		
	 }


