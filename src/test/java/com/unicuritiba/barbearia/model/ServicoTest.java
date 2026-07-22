package com.unicuritiba.barbearia.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ServicoTest {

	@Test
	void gettersAndSettersShouldPersistValues() {
		Servico servico = new Servico();

		servico.setId(3L);
		servico.setDescricao("Barba");
		servico.setUrl("http://example.com/barba.png");

		assertThat(servico.getId()).isEqualTo(3L);
		assertThat(servico.getDescricao()).isEqualTo("Barba");
		assertThat(servico.getUrl()).isEqualTo("http://example.com/barba.png");
	}

	@Test
	void newInstanceShouldHaveNullFields() {
		Servico servico = new Servico();

		assertThat(servico.getId()).isNull();
		assertThat(servico.getDescricao()).isNull();
		assertThat(servico.getUrl()).isNull();
	}
}
