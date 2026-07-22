package com.unicuritiba.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unicuritiba.barbearia.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{

	Funcionario findByCpf(String cpf);

}