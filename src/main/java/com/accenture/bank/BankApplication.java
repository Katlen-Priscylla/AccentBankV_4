package com.accenture.bank;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.repository.AgenciaRepository;
import com.accenture.bank.repository.ClienteRepository;
import com.accenture.bank.repository.ContaCorrenteRepository;


@SpringBootApplication
public class BankApplication implements CommandLineRunner{
	@Autowired
	AgenciaRepository agenciaRepository;
	@Autowired
	ContaCorrenteRepository contaCorrenteRepository;
	@Autowired
	ClienteRepository clienteRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		  Cliente cliente1 = new Cliente(null,"Joao e o pé de feijao","454654646-9","156565464");
			clienteRepository.save(cliente1);
		Agencia agencia = new Agencia(null,"Bradesco centro","Rua do centro",null);
  	    agencia = agenciaRepository.save(agencia);
		ContaCorrente conta1 = new ContaCorrente(null,"49-9",(double)700.5,agencia,cliente1);
		contaCorrenteRepository.save(conta1);
		
	
		
//		ContaCorrente conta1 = new ContaCorrente(null,"123-4",null,agencia1);
//		contas.add(conta1);
//		agencia1.getContas().addAll(contas);
//         agenciaRepository.save(agencia1);
//		
//		contaCorrenteRepository.save(conta1);
//		Cliente cliente1 = new Cliente(null,"João","123.456.789-12","1234.56789");
//		cliente1 = clienteRepository.save(cliente1);
//		
		
		
		
	}

}
