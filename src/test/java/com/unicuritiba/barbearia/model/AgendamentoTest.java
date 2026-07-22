package com.unicuritiba.barbearia.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AgendamentoTest {

	@Test
	void gettersAndSettersShouldPersistValues() {
		Agendamento agendamento = new Agendamento();

		agendamento.setId(10L);
		agendamento.setCliente("João");
		agendamento.setTelefone("41999998888");
		agendamento.setEmail("joao@example.com");
		agendamento.setServico("Corte");
		agendamento.setProfissional("Carlos");
		agendamento.setData("2026-07-22");
		agendamento.setHora("14:30");
		agendamento.setObservacao("Sem máquina");

		assertThat(agendamento.getId()).isEqualTo(10L);
		assertThat(agendamento.getCliente()).isEqualTo("João");
		assertThat(agendamento.getTelefone()).isEqualTo("41999998888");
		assertThat(agendamento.getEmail()).isEqualTo("joao@example.com");
		assertThat(agendamento.getServico()).isEqualTo("Corte");
		assertThat(agendamento.getProfissional()).isEqualTo("Carlos");
		assertThat(agendamento.getData()).isEqualTo("2026-07-22");
		assertThat(agendamento.getHora()).isEqualTo("14:30");
		assertThat(agendamento.getObservacao()).isEqualTo("Sem máquina");
	}

	@Test
	void newInstanceShouldHaveNullFields() {
		Agendamento agendamento = new Agendamento();

		assertThat(agendamento.getId()).isNull();
		assertThat(agendamento.getCliente()).isNull();
		assertThat(agendamento.getObservacao()).isNull();
	}
}
