package com.unicuritiba.barbearia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unicuritiba.barbearia.model.Agendamento;
import com.unicuritiba.barbearia.model.Funcionario;
import com.unicuritiba.barbearia.model.Servico;
import com.unicuritiba.barbearia.repository.AgendamentoRepository;
import com.unicuritiba.barbearia.repository.FuncionarioRepository;
import com.unicuritiba.barbearia.repository.ServicoRepository;

@Controller
public class HomeController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	private ModelAndView view(String viewName) {
		return new ModelAndView(viewName);
	}

	private ModelAndView deleteAndRedirect(JpaRepository<?, Long> repository, Integer id, String redirectView) {
		repository.deleteById(Long.valueOf(id));
		return new ModelAndView(redirectView);
	}

	@GetMapping("/")
	public ModelAndView home () {
		return view("home");
	}

	@GetMapping("/index")
	public ModelAndView index () {

		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		List<Servico> servicos = servicoRepository.findAll();

		ModelAndView pagina = view("index");
		pagina.addObject("agendamento", new Agendamento());
		pagina.addObject("funcionarios", funcionarios);
		pagina.addObject("servicos", servicos);
		return pagina;
	}

	@PostMapping("/agendamento-cliente")
	public ModelAndView agendamentoCliente (@ModelAttribute Agendamento agendamento) {

		agendamentoRepository.save(agendamento);

		return view("redirect:/sucesso-agendamento");
	}

	@GetMapping("/sucesso-agendamento")
	public ModelAndView boaAgendamento() {
		return view("sucessoAgendamento");
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro () {
		ModelAndView pagina = view("cadastro");
		pagina.addObject("funcionario", new Funcionario());
		return pagina;
	}

	@PostMapping("/cadastro-funcionario")
	public ModelAndView cadastroFuncionario(@ModelAttribute Funcionario funcionario) {

		funcionarioRepository.save(funcionario);
		return view("redirect:/sucesso-cadastro");
	}

	@GetMapping("/sucesso-cadastro")
	public ModelAndView bemVindoFuncionario() {
		return view("sucessoCadastro");
	}

	@GetMapping("/orderAdmin")
	public ModelAndView adminBarber() {

		List<Agendamento> agendamentos = agendamentoRepository.findAll();

		ModelAndView pagina = view("orderAdmin");
		pagina.addObject("agendamentos", agendamentos);

		return pagina;
	}

	@PostMapping("/deleta-agendamento")
	public ModelAndView deleteAgendamento (@RequestParam(value = "id", required = false) Integer id) {
		return deleteAndRedirect(agendamentoRepository, id, "redirect:/orderAdmin");
	}

	@GetMapping("/agenda")
	public ModelAndView agendA () {

		List<Funcionario> funcionarios = funcionarioRepository.findAll();

		List<Agendamento> agendamentos = agendamentoRepository.findAll();

		ModelAndView pagina = view("agenda");
		pagina.addObject("agendamentos", agendamentos);
		pagina.addObject("funcionarios", funcionarios);

		return pagina;
	}

	@GetMapping("/agenda/{funcionario}")
	public ModelAndView agenda (@PathVariable("funcionario") String profissional) {

		List<Agendamento> agendamentos = agendamentoRepository.findByProfissional(profissional);

		ModelAndView pagina = view("agenda");
		pagina.addObject("agendamento", agendamentos);
		return pagina;
	}

	@GetMapping("/login")
	public ModelAndView login () {
		return view("login");
	}

	@PostMapping("/valida-login")
	public ModelAndView validaLogin (@ModelAttribute Funcionario funcionario) {

		System.out.println(funcionario.getCpf());
		System.out.println(funcionario.getSenha());

		return view("redirect:/orderAdmin");
	}

	@GetMapping("/funcionarios")
	public ModelAndView Func () {

		List<Funcionario> funcionarios = funcionarioRepository.findAll();

		ModelAndView pagina = view("funcionarios");
		pagina.addObject("funcionarios", funcionarios);

		return pagina;
	}

	@PostMapping("/deleta-funcionario")
	public ModelAndView deleteFuncionario (@RequestParam(value = "id", required = false) Integer id) {
		return deleteAndRedirect(funcionarioRepository, id, "redirect:/funcionarios");
	}

	@GetMapping("/servicos")
	public ModelAndView Serv () {

		List<Servico> servicos = servicoRepository.findAll();

		ModelAndView pagina = view("servicos");
		pagina.addObject("servicos", servicos);

		return pagina;
	}

	@GetMapping("/cadastro-servico")
	public ModelAndView cadastroServ () {
		ModelAndView pagina = view("cadastro-servico");
		pagina.addObject("servico", new Servico());
		return pagina;
	}

	@PostMapping("/cadastroServ")
	public ModelAndView cadastroServicp (@ModelAttribute Servico servico) {

		servicoRepository.save(servico);
		return view("redirect:/servicos");
	}

	@PostMapping("/deleta-servico")
	public ModelAndView deleteServico (@RequestParam(value = "id", required = false) Integer id) {
		return deleteAndRedirect(servicoRepository, id, "redirect:/servicos");
	}

}
