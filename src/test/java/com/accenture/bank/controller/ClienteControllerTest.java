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

import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.Endereco;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.servico.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

	@MockBean
	private ClienteService clienteService;
	
	@MockBean
	private ClienteRepository clienteRepository;

	@Autowired
	MockMvc mvc;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deveRetornarUmaExcecaoCPFInvalido() throws Exception {
	
		//cenario
		final String CPF_INVALIDO ="123.456.789-00";
	
		String json = new ObjectMapper().writeValueAsString(new Cliente(null,"José",CPF_INVALIDO,"123.888",new Endereco()));
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/clientes")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
	
		
		//execução
		
		BDDMockito.given(clienteService.saveCliente(Mockito.any())).willThrow(javax.validation.ConstraintViolationException.class);
		
		
		//validação
		
Throwable clienteExcecao = Assertions.catchThrowable(() -> clienteService
		.saveCliente(new Cliente(null,"José",CPF_INVALIDO,"123.888",new Endereco())));
		
		Assertions.assertThat(clienteExcecao)
		.isInstanceOf(javax.validation.ConstraintViolationException.class);
	
}
	@Test
	public void deveRetornarUmClienteAPartirDeIdValido() throws Exception {
		String CPF_VALIDO ="092.832.534-26";
		Endereco endereco = new Endereco();
		//cenario
		Long idTeste = 1L;
	Cliente clienteTest = new Cliente(1L,"Joao",CPF_VALIDO,"123.456",endereco);
	
	BDDMockito.given(clienteService.getById(Mockito.anyLong())).willReturn(clienteTest);
		
	
	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/clientes/1")
			.accept(MediaType.APPLICATION_JSON);
			
		//execucao e validacao
	
	mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
	.andExpect(jsonPath("idCliente").value("1"))
	.andExpect(jsonPath("cpf").value(CPF_VALIDO))
	.andExpect(jsonPath("telefone").value("123.456"))
	.andExpect(jsonPath("nome").value("Joao"));
		
}

	@Test
	public void deveRetornarTodosOsClientes() throws Exception {
	
		//cenario
		
	 List<Cliente> clientesTest = new ArrayList<>();
		clientesTest.add(new Cliente ());
		clientesTest.add(new Cliente ());
		clientesTest.add(new Cliente ());
		clientesTest.add(new Cliente ());
		clientesTest.add(new Cliente ());
		
	
	BDDMockito.given(clienteService.getAllCliente()).willReturn(clientesTest);
		
	
	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/clientes/")
			.accept(MediaType.APPLICATION_JSON);
			
		//execucao e validacao
	
	mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
	.andExpect(jsonPath("$[*]", hasSize(5)));
		
	
}
	@Test
	public void deveDeletar() throws Exception {
	
		//cenario
//	
		String CPF_VALIDO ="092.832.534-26";
		Endereco endereco = new Endereco();
//		//cenario
//		Long idTeste = 1L;
	Cliente clienteTest = new Cliente(1L,"Joao",CPF_VALIDO,"123.456",endereco);
	
		BDDMockito.given(clienteService.getById(1L)).willReturn(clienteTest);
	
	MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/clientes/1")
			.accept(MediaType.APPLICATION_JSON);
			
		//execucao e validacao
	
	mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNoContent());
	
		
	
}
	
	@Test
	public void naoDeveDeletarByIdInexistente() throws Exception {
	
		//cenario
	//	
			String CPF_VALIDO ="092.832.534-26";
			Endereco endereco = new Endereco();
//			//cenario
//			Long idTeste = 1L;
		Cliente clienteTest = new Cliente(1L,"Joao",CPF_VALIDO,"123.456",endereco);
		
			BDDMockito.given(clienteRepository.existsById(123L)).willReturn(false);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/clientes/123")
				.accept(MediaType.APPLICATION_JSON);
				
			//execucao e validacao
		
		//mvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound());
		Mockito.verify(clienteRepository, Mockito.never()).deleteById(123L);
		
			
		
	
	
	
	}	
}
