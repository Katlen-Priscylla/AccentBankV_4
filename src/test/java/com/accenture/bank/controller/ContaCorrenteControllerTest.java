package com.accenture.bank.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mockitoSession;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Extrato;
import com.accenture.bank.entity.Transacao;
import com.accenture.bank.servico.ContaCorrenteService;
import com.accenture.bank.servico.ExtratoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContaCorrenteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContaCorrenteService service;
	
	@MockBean
	private ExtratoService extratoService;

	@Test
	public void deveRetornar200AoSalvarumaConta() throws Exception {
		URI uri = new URI("/contas/");

		String json = new ObjectMapper().writeValueAsString(criarContaValida());

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(json);
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	public void deveRetornarTodasAsContas() throws Exception {
		URI uri = new URI("/contas/");

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		List<ContaCorrente> contas = new ArrayList<>();
		
		contas.add(criarContaValida());
		contas.add(criarContaValida());
		contas.add(criarContaValida());
		contas.add(criarContaValida());
		
		BDDMockito.given(service.getAllContaCorrente()).willReturn(contas);
		
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(jsonPath("$[*]", hasSize(4)));
	}
	
	@Test
	public void deveRetornarUmaContaPeloId() throws Exception {
		URI uri = new URI("/contas/1");
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(uri)
				.accept(MediaType.APPLICATION_JSON);
		
		ContaCorrente conta = criarContaValida();
		conta.setIdContaCorrente(1L);
		
		BDDMockito.given(service.getByIdContaCorrente(Mockito.anyLong())).willReturn(conta);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect( jsonPath("idContaCorrente").value(1L))
		.andExpect(jsonPath("contaCorrenteNumero").value("1212"))
		.andExpect(jsonPath("saldo").value(50.0))
		;
		
	}
	
	@Test
	public void deveAlterarUmaContaPorId() throws Exception {
		URI uri = new URI("/contas/1");
		
		String json = new ObjectMapper().writeValueAsString(criarContaValida());
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		ContaCorrente conta = new ContaCorrente(1L, "2233", 60.0, new Agencia(), new Cliente(), new ArrayList<Extrato>());
		conta.setIdContaCorrente(1L);
		
		BDDMockito.given(service.atualizaContaCorrente(Mockito.anyLong(), Mockito.any())).willReturn(conta);
		
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(jsonPath("contaCorrenteNumero").value("2233"))
		.andExpect(jsonPath("saldo").value(60.0))
		;
	}
	
	@Test
	public void deveDeletarUmaContaPeloId() throws Exception{
		URI uri = new URI("/contas/1");
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.delete(uri)
				.accept(MediaType.APPLICATION_JSON)
				;
		
		mockMvc.perform(request)
		.andExpect(status().isNoContent());
		
	}
	
	@Test
	public void deveSacarValorNaContaPassadaPeloId() throws Exception {
		URI uri = new URI("/contas/1/sacar");
		final Double VALOR_SAQUE = 10.0;
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(Double.toString(VALOR_SAQUE))
				;
		ContaCorrente contaResponse = new ContaCorrente(null, "1212", 40.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		BDDMockito.given(service.saque(Mockito.anyLong(), Mockito.anyDouble(), Mockito.any())).willReturn(contaResponse);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("saldo").value(40.0))
		;
	}
	
	@Test
	public void deveDepositarValorNaContaPassadaPeloId() throws Exception{
		URI uri = new URI("/contas/1/depositar");
		final String VALOR_DEPOSITADO = "10";
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.put(uri)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(VALOR_DEPOSITADO)
				;
		
		ContaCorrente contaResponse = new ContaCorrente(null, "1212", 60.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		BDDMockito.given(service.deposita(Mockito.anyLong(), Mockito.anyDouble(), Mockito.any())).willReturn(contaResponse);
		
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(jsonPath("saldo").value(60.0))
		;
	}
	@Test
	public void deveTransferirValorParaContasDiferentes() throws Exception{
	URI uri = new URI("/contas/1/transferir/2");
	
	final String VALOR_TRANSFERIDO = "10";
	
	MockHttpServletRequestBuilder request = MockMvcRequestBuilders
			.put(uri)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(VALOR_TRANSFERIDO)
			;
	
	ContaCorrente contaResponseOrigem = new ContaCorrente(1L, "1212", 60.0, new Agencia(), new Cliente(),
			new ArrayList<Extrato>());
	ContaCorrente contaResponseDestino = new ContaCorrente(2L, "2233", 40.0, new Agencia(), new Cliente(),
			new ArrayList<Extrato>());
	
	
	Map<String, ContaCorrente> retornoTransferencia = new HashMap<>();
	
	retornoTransferencia.put("origem", contaResponseOrigem);
	retornoTransferencia.put("destino", contaResponseDestino);
	
	BDDMockito.given(service.transfere(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyDouble())).willReturn(retornoTransferencia);
	
	mockMvc.perform(request)
	.andExpect(MockMvcResultMatchers.status().is(200))
	.andExpect(jsonPath("$.origem['saldo']").value(60.0))
	.andExpect(jsonPath("$.destino['saldo']").value(40.0))
	.andExpect(jsonPath("$.origem['idContaCorrente']").value(1))
	.andExpect(jsonPath("$.destino['idContaCorrente']").value(2))
	
	;

	}
	
	@Test
	public void deveRetornarExtratoPorId() throws Exception{
		URI uri = new URI("/contas/1/extrato");
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		List<Extrato> extratos = new ArrayList<>();
		
		extratos.add(new Extrato());
		extratos.add(new Extrato());
		extratos.add(new Extrato());
		extratos.add(new Extrato());
		
		BDDMockito.given(extratoService.getByContaId(1L)).willReturn(extratos);
		
		mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is(200))
		.andExpect(jsonPath("$[*]", hasSize(4)));
		
		
	}
	
	private ContaCorrente criarContaValida() {
		ContaCorrente contaValida = new ContaCorrente(null, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		return contaValida;
	}

}
