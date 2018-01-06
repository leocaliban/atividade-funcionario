package com.leocaliban.empresa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
		mv.addObject(new Funcionario());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Funcionario funcionario, Errors errors) {
		ModelAndView mv = new ModelAndView("CadastroFuncionario");
		if(errors.hasErrors()) {
			return mv;
		}
		service.salvar(funcionario);
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
