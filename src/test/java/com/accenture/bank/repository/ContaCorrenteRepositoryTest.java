package com.accenture.bank.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.accenture.bank.entity.Agencia;
import com.accenture.bank.entity.Cliente;
import com.accenture.bank.entity.ContaCorrente;
import com.accenture.bank.entity.Extrato;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContaCorrenteRepositoryTest {

	@Autowired
	private ContaCorrenteRepository repository;

	@Test
	public void deveRetornarUmaContaPorId() {

		long idRequerido = 1L;

		Optional<ContaCorrente> contaRetornada = repository.findById(idRequerido);

		assertThat(contaRetornada.get().getIdContaCorrente())
		.isEqualTo(idRequerido);

	}

	@Test
	public void deveRetornarTodasAsContas() {

		List<ContaCorrente> contas = repository.findAll();

		assertThat(contas).isNotEmpty();
		org.assertj.core.api.Assertions.assertThat(contas).hasSize(2);

	}
	
	@Test
	public void deveSalvarUmaContaComSucesso() {
				
		ContaCorrente contaSalva =  repository.save(criarContaValida());
		
		assertThat(contaSalva.getIdContaCorrente()).isNotNull();
		assertThat(contaSalva.getSaldo()).isEqualTo(criarContaValida().getSaldo());
		assertThat(contaSalva.getCliente()).isEqualTo(criarContaValida().getCliente());
		assertThat(contaSalva.getAgencia()).isEqualTo(criarContaValida().getAgencia());
		
		
	}
	
	@Test
	public void deveDeletarUmContaComSucesso() {
		
		assertThat(repository.existsById(1L)).isTrue();
		
		repository.deleteById(1L);
		
		assertThat(repository.existsById(1L)).isFalse();
	}
	
	private ContaCorrente criarContaValida() {
		ContaCorrente contaValida = new ContaCorrente(null, "1212", 50.0, new Agencia(), new Cliente(),
				new ArrayList<Extrato>());
		contaValida.getAgencia().setIdAgencia(1L);
		contaValida.getCliente().setIdCliente(1L);
		return contaValida;
	}
}
