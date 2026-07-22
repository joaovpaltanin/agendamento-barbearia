package com.unicuritiba.barbearia.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unicuritiba.barbearia.model.Funcionario;
import com.unicuritiba.barbearia.repository.FuncionarioRepository;

@Service
public class FuncionarioDetailsService implements UserDetailsService {

	private final FuncionarioRepository funcionarioRepository;

	public FuncionarioDetailsService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Funcionario funcionario = funcionarioRepository.findByCpf(cpf);
		if (funcionario == null) {
			throw new UsernameNotFoundException("Funcionário não encontrado: " + cpf);
		}
		return new User(
			funcionario.getCpf(),
			funcionario.getSenha(),
			Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
		);
	}
}
