package com.unicuritiba.barbearia.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FuncionarioTest {

	@Test
	void gettersAndSettersShouldPersistValues() {
		Funcionario funcionario = new Funcionario();

		funcionario.setId(5L);
		funcionario.setNome("Carlos");
		funcionario.setCpf("12345678900");
		funcionario.setSenha("secret");

		assertThat(funcionario.getId()).isEqualTo(5L);
		assertThat(funcionario.getNome()).isEqualTo("Carlos");
		assertThat(funcionario.getCpf()).isEqualTo("12345678900");
		assertThat(funcionario.getSenha()).isEqualTo("secret");
	}

	@Test
	void newInstanceShouldHaveNullFields() {
		Funcionario funcionario = new Funcionario();

		assertThat(funcionario.getId()).isNull();
		assertThat(funcionario.getNome()).isNull();
		assertThat(funcionario.getCpf()).isNull();
		assertThat(funcionario.getSenha()).isNull();
	}
}
