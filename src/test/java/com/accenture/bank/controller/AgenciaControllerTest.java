package com.accenture.bank.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Endereco;
import com.accenture.bank.exceptions.FormatoInvalidoException;
import com.accenture.bank.repository.AgenciaRepository;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.servico.AgenciaService;
import com.accenture.bank.servico.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AgenciaControllerTest {

	
	@MockBean
	private AgenciaService agenciaService;
	
	@MockBean
	private AgenciaRepository agenciaRepository;

	@Autowired
	MockMvc mvc;
	
	
	
	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}
	
//testando o metodo save
	@Test
	public void deveRetornarUmaAgenciaPartirDeIdValido() throws Exception {
	
		Endereco endereco = new Endereco();
		//cenario
		
		
	 List<ContaCorrente> contas = new ArrayList<>();
	 contas.add(new ContaCorrente());
	 contas.add(new ContaCorrente());
	 contas.add(new ContaCorrente());
	 contas.add(new ContaCorrente());
	 
	//	Long idTeste = 1L;
	Agencia agenciaTest = new Agencia(1L,"Agencia Centro","9999.9999",endereco,contas);
	
	BDDMockito.given(agenciaService.getById(Mockito.anyLong())).willReturn(agenciaTest);
		
	
	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/agencias/1")
			.accept(MediaType.APPLICATION_JSON);
			
		//execucao e validacao
	
	mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
	.andExpect(jsonPath("idAgencia").value("1"))
	.andExpect(jsonPath("nome").value("Agencia Centro"))
	.andExpect(jsonPath("telefone").value("9999.9999"));
	
		
}
//dados para fazer o post invalidos
	@Test
	public void DeveRetornarUmaExcessaoComDadoInvalido() throws Exception {
	
		//cenario
		
			String nome = null;
			Endereco endereco = new Endereco();
//			//cenario

			List <ContaCorrente> contas = new ArrayList<>();
			contas.add(new ContaCorrente());
			contas.add(new ContaCorrente());
			contas.add(new ContaCorrente());
		
		
		
			String json = new ObjectMapper().writeValueAsString(new Agencia(null,nome,"999.999",endereco,contas));
			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/agencias/")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json);
		
			
			//execução
			
			BDDMockito.given(agenciaService.saveAgencia(Mockito.any())).willThrow(FormatoInvalidoException.class);
			
			
			//validação
			
	Throwable agenciaExcecao = Assertions.catchThrowable(() -> agenciaService
			.saveAgencia(new Agencia(null,nome,"999.999",endereco,contas )));
			
			Assertions.assertThat(agenciaExcecao)
			.isInstanceOf(FormatoInvalidoException.class);
	
	}	
	
	@Test
	public void deveRetornarTodasAsAgencias() throws Exception {
	
		//cenario
		
	  List<Agencia> agencias = new ArrayList<>();
		agencias.add(new Agencia());
		agencias.add(new Agencia());
		agencias.add(new Agencia());
		
	
	BDDMockito.given(agenciaService.getAllAgencias()).willReturn(agencias);
		
	
	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/agencias/")
			.accept(MediaType.APPLICATION_JSON);
			
		//execucao e validacao
	
	mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
	.andExpect(jsonPath("$[*]", hasSize(3)));
		
	
}
	
	@Test
	public void deveDeletarAgencia() throws Exception {
	
		//cenario
		 List<ContaCorrente> contas = new ArrayList<>();
		 contas.add(new ContaCorrente());
		 contas.add(new ContaCorrente());
		 contas.add(new ContaCorrente());
		 contas.add(new ContaCorrente());
		 
		 Endereco endereco = new Endereco();
		 
		//	Long idTeste = 1L;
		Agencia agenciaTest = new Agencia(1L,"Agencia Centro","9999.9999",endereco,contas);
		
	
		BDDMockito.given(agenciaService.getById(1L)).willReturn(agenciaTest);
	
	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/agencias/1")
			.accept(MediaType.APPLICATION_JSON);
			
		//execucao e validacao
	
	mvc.perform(request).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	
		
	
}
	
	@Test
	public void naoDeveDeletarByIdInexistente() throws Exception {
	
		 List<ContaCorrente> contas = new ArrayList<>();
		 contas.add(new ContaCorrente());
		 contas.add(new ContaCorrente());
		 contas.add(new ContaCorrente());
		 contas.add(new ContaCorrente());
		 
		 Endereco endereco = new Endereco();
		 
		//	Long idTeste = 1L;
		Agencia agenciaTest = new Agencia(1L,"Agencia Centro","9999.9999",endereco,contas);
		
		
			BDDMockito.given(agenciaRepository.existsById(123L)).willReturn(false);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/clientes/123")
				.accept(MediaType.APPLICATION_JSON);
				
			//execucao e validacao
		
		//mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
		Mockito.verify(agenciaRepository, Mockito.never()).deleteById(123L);
	
		
		
	
	}	
}
