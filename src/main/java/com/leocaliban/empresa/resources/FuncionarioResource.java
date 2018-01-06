package com.leocaliban.empresa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.leocaliban.empresa.domain.Funcionario;
import com.leocaliban.empresa.services.FuncionarioService;

@Controller
@RequestMapping(value="/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService service;
		
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroFuncionario");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Funcionario funcionario) {
		service.salvar(funcionario);
		ModelAndView mv = new ModelAndView("CadastroFuncionario");
		mv.addObject("mensagem","Funcionário Cadastrado Com Sucesso!");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Funcionario> funcionarios = service.listarTodos();
		ModelAndView mv = new ModelAndView("PesquisaFuncionario");
		mv.addObject("funcionarios", funcionarios);
		return mv;

	}
	
}
