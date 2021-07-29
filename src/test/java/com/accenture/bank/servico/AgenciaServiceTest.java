package com.accenture.bank.servico;

import java.util.ArrayList;
import java.util.List;

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
import com.accenture.bank.entity.Endereco;
import com.accenture.bank.exceptions.AgenciaNaoEncontradaException;
import com.accenture.bank.exceptions.ClienteNotFoundException;
import com.accenture.bank.exceptions.FormatoIncorretoCliente;
import com.accenture.bank.exceptions.FormatoInvalidoException;
import com.accenture.bank.repository.AgenciaRepository;
import com.accenture.bank.repository.ClienteRepository;

public class AgenciaServiceTest {
	
	
	
	@InjectMocks
	 private AgenciaService agenciaService;

	@Mock
	private AgenciaRepository agenciaRepository;
	
	@BeforeEach
   public void setUp() {
		

		MockitoAnnotations.initMocks(this);
   }

	
	@Test
	public void deveLevantarExcecaoQuandoNaoEncontrarIdnoDeleteAgencia() {
		// cenario
		long idInvalido = 123L;
		

	Throwable agenciaExcecao = Assertions.catchThrowable(() -> agenciaService.deletaAgencia(idInvalido));
	
	Assertions.assertThat(agenciaExcecao)
	.isInstanceOf(AgenciaNaoEncontradaException.class)
	.hasMessage("Agencia Nao Encontrada");

	}
	
	@Test
	public void deveSalvarAgenciaComSucesso() {
		
		//cenario
		Endereco endereco = new Endereco();

		List <ContaCorrente> contas = new ArrayList<>();
		contas.add(new ContaCorrente());
		contas.add(new ContaCorrente());
		contas.add(new ContaCorrente());
		
		
		Agencia agenciaTest = new Agencia(1L,"Agencia Centro","9999.9999",endereco,contas);
		
		Mockito.when(agenciaRepository.save(Mockito.any())).thenReturn(agenciaTest);
		
		Agencia agenciaSalva=agenciaRepository.save (new Agencia(null,"Agencia Centro","9999.9999",endereco,contas));
		
		Assertions.assertThat(agenciaSalva).isEqualTo(agenciaTest);
	}

	@Test
	public void NaodeveSalvarAgenciaForaDeFormato() {
		//cenario
				Endereco endereco = new Endereco();

				List <ContaCorrente> contas = new ArrayList<>();
				contas.add(new ContaCorrente());
				contas.add(new ContaCorrente());
				contas.add(new ContaCorrente());
				
				
				
		Agencia agenciaTeste = new Agencia(1L,"Agencia Centro","",endereco,contas);
		
		Throwable agenciaExcecao = Assertions.catchThrowable(() -> agenciaService.saveAgencia(agenciaTeste));
		
		Assertions.assertThat(agenciaExcecao)
		.isInstanceOf(FormatoInvalidoException.class)
		.hasMessage("formato Invalido");
	
	}
	
	@Test
	public void deveRetornarTodosOsClientes(){
		
		Endereco endereco = new Endereco();

		List <ContaCorrente> contas = new ArrayList<>();
		contas.add(new ContaCorrente());
		contas.add(new ContaCorrente());
		contas.add(new ContaCorrente());
		
		List<Agencia> agencias = new ArrayList<>();
		
		Agencia agencia= new Agencia (1L,"Agencia Test","9999.999",endereco,contas);
		Agencia agencia1= new Agencia (1L,"Agencia Test2","9999.999",endereco,contas);
		Agencia agencia2= new Agencia (1L,"Agencia Test3","9999.999",endereco,contas);

		agencias.add(agencia);
		agencias.add(agencia1);
		agencias.add(agencia2);

		Mockito.when(agenciaRepository.findAll()).thenReturn(agencias);
		
		Assertions.assertThat(agencias).isEqualTo(agenciaRepository.findAll());
	}
	
	
	@Test
	public void deveLevantarExcecaoQuandoNaoEncontrarIdnoGet() {
		// cenario
		long idInvalido = 123L;
		

	Throwable agenciaExcecao = Assertions.catchThrowable(() -> agenciaService.getById(idInvalido));
	
	Assertions.assertThat(agenciaExcecao)
	.isInstanceOf(AgenciaNaoEncontradaException.class)
	.hasMessage("Agencia Nao Encontrada");

	}

}
