package com.unicuritiba.barbearia.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.unicuritiba.barbearia.model.Funcionario;
import com.unicuritiba.barbearia.repository.FuncionarioRepository;

/**
 * Seeds an initial admin account so the login flow can be bootstrapped without
 * inserting a BCrypt-hashed password by hand. Runs only when both ADMIN_CPF and
 * ADMIN_SENHA are provided and the account does not already exist.
 */
@Component
public class DataInitializer implements CommandLineRunner {

	private final FuncionarioRepository funcionarioRepository;
	private final PasswordEncoder passwordEncoder;

	@Value("${barbearia.admin.cpf:}")
	private String adminCpf;

	@Value("${barbearia.admin.senha:}")
	private String adminSenha;

	@Value("${barbearia.admin.nome:Administrador}")
	private String adminNome;

	public DataInitializer(FuncionarioRepository funcionarioRepository, PasswordEncoder passwordEncoder) {
		this.funcionarioRepository = funcionarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {
		if (adminCpf == null || adminCpf.isEmpty() || adminSenha == null || adminSenha.isEmpty()) {
			return;
		}
		if (funcionarioRepository.findByCpf(adminCpf) != null) {
			return;
		}
		Funcionario admin = new Funcionario();
		admin.setNome(adminNome);
		admin.setCpf(adminCpf);
		admin.setSenha(passwordEncoder.encode(adminSenha));
		funcionarioRepository.save(admin);
	}
}
