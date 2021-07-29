package com.accenture.bank.entity;


import org.junit.jupiter.api.Test;

import junit.framework.TestCase;


public class ClienteTest extends TestCase {

	Cliente cliente = new Cliente();
	
	@Test
	public void testSetandGetCliente() {
		
		String testeNome = "Maria";
		assertNull(cliente.getNome());
		cliente.setNome(testeNome);
		assertEquals(testeNome, cliente.getNome());
		
		 String testeCpf = "123.456.789-89";
		 assertNull(cliente.getCpf());
		 cliente.setCpf(testeCpf);
		 assertEquals(testeCpf,cliente.getCpf());
		 
		 String telefone = "1111-2222";
		 assertNull(cliente.getTelefone());
		 cliente.setTelefone(telefone);
		 assertEquals(telefone,cliente.getTelefone());
		 
		 
	}

	 @Test
	 public void testSetandGetObjeto() {
		 
		 Endereco endereco = new Endereco(null,"55555-000","Rua","casa","Qualqer","localidade","QX","1236","asabusab","099","123789","487");
		 
		assertNull(cliente.getEndereco());
			cliente.setEndereco(endereco);
			assertEquals(endereco, cliente.getEndereco());
			
//		    private String logradouro;
//		    private String complemento;
//		    private String bairro;
//		    private String localidade;
//		    private String uf;
//		    private String ibge;
//		    private String gia;
//		    private String ddd;
//		    private String siafi;
//		    private String numero;
	 }
	
}
