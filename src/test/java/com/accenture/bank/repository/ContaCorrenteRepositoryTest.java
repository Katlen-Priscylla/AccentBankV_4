package com.accenture.bank.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Extrato;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContaCorrenteRepositoryTest {

	@MockBean
	private ContaCorrenteRepository repository;

	@Test
	public void deveRetornarUmaContaPorId() {

		long idRequerido = 1L;
		
		ContaCorrente mockConta = new ContaCorrente(idRequerido, "9999", 100.0, new Agencia(), new Cliente(), new ArrayList<Extrato>());
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockConta) );

		Optional<ContaCorrente> contaRetornada = repository.findById(idRequerido);

		assertThat(contaRetornada.get().getIdContaCorrente())
		.isEqualTo(idRequerido);

	}

	@Test
	public void deveRetornarTodasAsContas() {
		
		List<ContaCorrente> mockContas = new ArrayList<ContaCorrente>();
		mockContas.add(new ContaCorrente());
		mockContas.add(new ContaCorrente());
		
		Mockito.when(repository.findAll()).thenReturn(mockContas );

		List<ContaCorrente> contas = repository.findAll();

		assertThat(contas).isNotEmpty();
		org.assertj.core.api.Assertions.assertThat(contas).hasSize(2);

	}
	
	@Test
	public void deveSalvarUmaContaComSucesso() {
		
		ContaCorrente mockConta = new ContaCorrente(1L, "9999", 50.0, new Agencia(), new Cliente(), new ArrayList<Extrato>());
		Mockito.when(repository.save(Mockito.any())).thenReturn(mockConta);
		
		ContaCorrente contaSalva =  repository.save(criarContaValida());
		
		assertThat(contaSalva.getIdContaCorrente()).isNotNull();
		assertThat(contaSalva.getSaldo()).isEqualTo(mockConta.getSaldo());
		assertThat(contaSalva.getCliente()).isEqualTo(mockConta.getCliente());
		assertThat(contaSalva.getAgencia()).isEqualTo(mockConta.getAgencia());
		
		
	}
	
	@Test
	public void deveDeletarUmContaComSucesso() {
		
		ContaCorrente mockConta = new ContaCorrente(1L, "9999", 50.0, new Agencia(), new Cliente(), new ArrayList<Extrato>());
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(mockConta));
			
				
		repository.deleteById(1L);
		
		Mockito.verify(repository,times(1)).deleteById(1L);
	}
	
	private ContaCorrente criarContaValida() {
		ContaCorrente contaValida = new ContaCorrente(1L, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		return contaValida;
	}
}
