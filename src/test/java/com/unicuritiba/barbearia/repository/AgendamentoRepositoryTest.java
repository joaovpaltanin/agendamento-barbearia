package com.unicuritiba.barbearia.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.unicuritiba.barbearia.model.Agendamento;

@DataJpaTest
class AgendamentoRepositoryTest {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	private Agendamento newAgendamento(String cliente, String profissional) {
		Agendamento agendamento = new Agendamento();
		agendamento.setCliente(cliente);
		agendamento.setProfissional(profissional);
		return agendamento;
	}

	@Test
	void findByProfissionalShouldReturnOnlyMatchingAgendamentos() {
		agendamentoRepository.save(newAgendamento("João", "Carlos"));
		agendamentoRepository.save(newAgendamento("Maria", "Carlos"));
		agendamentoRepository.save(newAgendamento("Ana", "Rafael"));

		List<Agendamento> carlos = agendamentoRepository.findByProfissional("Carlos");

		assertThat(carlos).hasSize(2);
		assertThat(carlos).extracting(Agendamento::getProfissional).containsOnly("Carlos");
	}

	@Test
	void findByProfissionalShouldReturnEmptyWhenNoMatch() {
		agendamentoRepository.save(newAgendamento("João", "Carlos"));

		assertThat(agendamentoRepository.findByProfissional("Desconhecido")).isEmpty();
	}
}
