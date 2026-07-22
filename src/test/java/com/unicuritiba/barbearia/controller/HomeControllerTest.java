package com.unicuritiba.barbearia.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import com.unicuritiba.barbearia.model.Agendamento;
import com.unicuritiba.barbearia.model.Funcionario;
import com.unicuritiba.barbearia.model.Servico;
import com.unicuritiba.barbearia.repository.AgendamentoRepository;
import com.unicuritiba.barbearia.repository.FuncionarioRepository;
import com.unicuritiba.barbearia.repository.ServicoRepository;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

	@Mock
	private FuncionarioRepository funcionarioRepository;

	@Mock
	private AgendamentoRepository agendamentoRepository;

	@Mock
	private ServicoRepository servicoRepository;

	@InjectMocks
	private HomeController controller;

	@Test
	void homeShouldReturnHomeView() {
		ModelAndView mav = controller.home();

		assertThat(mav.getViewName()).isEqualTo("home");
	}

	@Test
	void indexShouldLoadFuncionariosServicosAndNewAgendamento() {
		List<Funcionario> funcionarios = List.of(new Funcionario());
		List<Servico> servicos = List.of(new Servico());
		when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		when(servicoRepository.findAll()).thenReturn(servicos);

		ModelAndView mav = controller.index();

		assertThat(mav.getViewName()).isEqualTo("index");
		assertThat(mav.getModel().get("funcionarios")).isEqualTo(funcionarios);
		assertThat(mav.getModel().get("servicos")).isEqualTo(servicos);
		assertThat(mav.getModel().get("agendamento")).isInstanceOf(Agendamento.class);
	}

	@Test
	void agendamentoClienteShouldSaveAndRedirect() {
		Agendamento agendamento = new Agendamento();

		ModelAndView mav = controller.agendamentoCliente(agendamento);

		verify(agendamentoRepository).save(agendamento);
		assertThat(mav.getViewName()).isEqualTo("redirect:/sucesso-agendamento");
	}

	@Test
	void boaAgendamentoShouldReturnSucessoView() {
		assertThat(controller.boaAgendamento().getViewName()).isEqualTo("sucessoAgendamento");
	}

	@Test
	void cadastroShouldExposeNewFuncionario() {
		ModelAndView mav = controller.cadastro();

		assertThat(mav.getViewName()).isEqualTo("cadastro");
		assertThat(mav.getModel().get("funcionario")).isInstanceOf(Funcionario.class);
	}

	@Test
	void cadastroFuncionarioShouldSaveAndRedirect() {
		Funcionario funcionario = new Funcionario();

		ModelAndView mav = controller.cadastroFuncionario(funcionario);

		verify(funcionarioRepository).save(funcionario);
		assertThat(mav.getViewName()).isEqualTo("redirect:/sucesso-cadastro");
	}

	@Test
	void bemVindoFuncionarioShouldReturnSucessoView() {
		assertThat(controller.bemVindoFuncionario().getViewName()).isEqualTo("sucessoCadastro");
	}

	@Test
	void adminBarberShouldLoadAgendamentos() {
		List<Agendamento> agendamentos = List.of(new Agendamento());
		when(agendamentoRepository.findAll()).thenReturn(agendamentos);

		ModelAndView mav = controller.adminBarber();

		assertThat(mav.getViewName()).isEqualTo("orderAdmin");
		assertThat(mav.getModel().get("agendamentos")).isEqualTo(agendamentos);
	}

	@Test
	void deleteAgendamentoShouldDeleteByIdAndRedirect() {
		ModelAndView mav = controller.deleteAgendamento(7);

		ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
		verify(agendamentoRepository).deleteById(captor.capture());
		assertThat(captor.getValue()).isEqualTo(7L);
		assertThat(mav.getViewName()).isEqualTo("redirect:/orderAdmin");
	}

	@Test
	void agendAShouldLoadFuncionariosAndAgendamentos() {
		List<Funcionario> funcionarios = List.of(new Funcionario());
		List<Agendamento> agendamentos = List.of(new Agendamento());
		when(funcionarioRepository.findAll()).thenReturn(funcionarios);
		when(agendamentoRepository.findAll()).thenReturn(agendamentos);

		ModelAndView mav = controller.agendA();

		assertThat(mav.getViewName()).isEqualTo("agenda");
		assertThat(mav.getModel().get("funcionarios")).isEqualTo(funcionarios);
		assertThat(mav.getModel().get("agendamentos")).isEqualTo(agendamentos);
	}

	@Test
	void agendaByProfissionalShouldFilterAgendamentos() {
		List<Agendamento> agendamentos = List.of(new Agendamento());
		when(agendamentoRepository.findByProfissional("Carlos")).thenReturn(agendamentos);

		ModelAndView mav = controller.agenda("Carlos");

		assertThat(mav.getViewName()).isEqualTo("agenda");
		assertThat(mav.getModel().get("agendamento")).isEqualTo(agendamentos);
	}

	@Test
	void loginShouldReturnLoginView() {
		assertThat(controller.login().getViewName()).isEqualTo("login");
	}

	@Test
	void validaLoginShouldRedirectToOrderAdmin() {
		Funcionario funcionario = new Funcionario();
		funcionario.setCpf("12345678900");
		funcionario.setSenha("secret");

		ModelAndView mav = controller.validaLogin(funcionario);

		assertThat(mav.getViewName()).isEqualTo("redirect:/orderAdmin");
	}

	@Test
	void funcShouldLoadFuncionarios() {
		List<Funcionario> funcionarios = List.of(new Funcionario());
		when(funcionarioRepository.findAll()).thenReturn(funcionarios);

		ModelAndView mav = controller.Func();

		assertThat(mav.getViewName()).isEqualTo("funcionarios");
		assertThat(mav.getModel().get("funcionarios")).isEqualTo(funcionarios);
	}

	@Test
	void deleteFuncionarioShouldDeleteByIdAndRedirect() {
		ModelAndView mav = controller.deleteFuncionario(4);

		ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
		verify(funcionarioRepository).deleteById(captor.capture());
		assertThat(captor.getValue()).isEqualTo(4L);
		assertThat(mav.getViewName()).isEqualTo("redirect:/funcionarios");
	}

	@Test
	void servShouldLoadServicos() {
		List<Servico> servicos = List.of(new Servico());
		when(servicoRepository.findAll()).thenReturn(servicos);

		ModelAndView mav = controller.Serv();

		assertThat(mav.getViewName()).isEqualTo("servicos");
		assertThat(mav.getModel().get("servicos")).isEqualTo(servicos);
	}

	@Test
	void cadastroServShouldExposeNewServico() {
		ModelAndView mav = controller.cadastroServ();

		assertThat(mav.getViewName()).isEqualTo("cadastro-servico");
		assertThat(mav.getModel().get("servico")).isInstanceOf(Servico.class);
	}

	@Test
	void cadastroServicpShouldSaveAndRedirect() {
		Servico servico = new Servico();

		ModelAndView mav = controller.cadastroServicp(servico);

		verify(servicoRepository).save(servico);
		assertThat(mav.getViewName()).isEqualTo("redirect:/servicos");
	}

	@Test
	void deleteServicoShouldDeleteByIdAndRedirect() {
		ModelAndView mav = controller.deleteServico(9);

		ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
		verify(servicoRepository).deleteById(captor.capture());
		assertThat(captor.getValue()).isEqualTo(9L);
		assertThat(mav.getViewName()).isEqualTo("redirect:/servicos");
	}
}
