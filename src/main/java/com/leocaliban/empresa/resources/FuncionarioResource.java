package com.leocaliban.empresa.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.leocaliban.empresa.domain.Funcionario;
import com.leocaliban.empresa.repositories.filters.FuncionarioFilter;
import com.leocaliban.empresa.services.FuncionarioService;

@Controller
@RequestMapping(value="/funcionarios")
public class FuncionarioResource {
	private static final String TELA_CADASTRO = "CadastroFuncionario";
	private static final String TELA_EDITAR = "EditaFuncionario";
	
	@Autowired
	private FuncionarioService service;
		
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(TELA_CADASTRO);
		mv.addObject(new Funcionario());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Funcionario funcionario, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return TELA_CADASTRO;
		}
		try {
			service.salvar(funcionario);	
			attributes.addFlashAttribute("mensagem","Funcionário Cadastrado Com Sucesso!");
			return "redirect:/funcionarios/novo";
		}
		catch(IllegalArgumentException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return TELA_CADASTRO;
		}	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar(@ModelAttribute("filtro")FuncionarioFilter filtro) {
		List<Funcionario> funcionarios = service.filtrar(filtro);
		ModelAndView mv = new ModelAndView("PesquisaFuncionario");
		mv.addObject("funcionarios", funcionarios);
		return mv;
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		Funcionario funcionario = service.editar(id);
		ModelAndView mv = new ModelAndView(TELA_EDITAR);
		mv.addObject(funcionario);
		return mv;
	}
		
	@RequestMapping(value="/editar",method = RequestMethod.POST)
	public ModelAndView editar(@Validated Funcionario funcionario,Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView(TELA_EDITAR);
		if(errors.hasErrors()) {
			return mv;
		}
		try {
			service.salvar(funcionario);	
			mv = new ModelAndView("redirect:/funcionarios");
			attributes.addFlashAttribute("mensagem","Edição Salva Com Sucesso!");
			return mv;
		}
		catch(IllegalArgumentException e) {
			errors.rejectValue("dataNascimento", null, e.getMessage());
			return mv;
		}
		
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		service.excluir(id);
		attributes.addFlashAttribute("mensagem", "Funcionário Excluído Com Sucesso!");
		return "redirect:/funcionarios";
	}
	
	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
	public @ResponseBody String mudarStatus(@PathVariable Long id) {
		return service.mudarStatus(id);
	}
	
	
}
